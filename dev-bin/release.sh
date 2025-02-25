#!/bin/bash

set -eu -o pipefail

changelog=$(cat CHANGELOG.md)

regex='
([0-9]+\.[0-9]+\.[0-9]+(-[^ ]+)?) \(([0-9]{4}-[0-9]{2}-[0-9]{2})\)
-*

((.|
)*)
'

if [[ ! $changelog =~ $regex ]]; then
      echo "Could not find date line in change log!"
      exit 1
fi

version="${BASH_REMATCH[1]}"
date="${BASH_REMATCH[3]}"
notes="$(echo "${BASH_REMATCH[4]}" | sed -n -E '/^[0-9]+\.[0-9]+\.[0-9]+/,$!p')"

if [[ "$date" != $(date +"%Y-%m-%d") ]]; then
    echo "$date is not today!"
    exit 1
fi

tag="v$version"

if [ -n "$(git status --porcelain)" ]; then
    echo ". is not clean." >&2
    exit 1
fi

if [ ! -d .gh-pages ]; then
    echo "Checking out gh-pages in .gh-pages"
    git clone -b gh-pages git@github.com:maxmind/minfraud-api-java.git .gh-pages
    pushd .gh-pages
else
    echo "Updating .gh-pages"
    pushd .gh-pages
    git pull
fi

if [ -n "$(git status --porcelain)" ]; then
    echo ".gh-pages is not clean" >&2
    exit 1
fi

popd

mvn versions:display-plugin-updates
mvn versions:display-dependency-updates

read -e -p "Continue given above dependencies? (y/n) " should_continue

if [ "$should_continue" != "y" ]; then
    echo "Aborting"
    exit 1
fi

page=.gh-pages/index.md
cat <<EOF > $page
---
layout: default
title: MaxMind minFraud Score and Insights Java API
language: java
version: $tag
---

EOF

perl -pi -e "s/(?<=<version>)[^<]*/$version/" README.md
perl -pi -e "s/(?<=com\.maxmind\.minfraud\:minfraud\:)\d+\.\d+\.\d+[^']*/$version/" README.md

cat README.md >> $page

if [ -n "$(git status --porcelain)" ]; then
    git diff

    read -e -p "Commit README.md changes? " should_commit
    if [ "$should_commit" != "y" ]; then
        echo "Aborting"
        exit 1
    fi
    git add README.md
    git commit -m 'update version number in README.md'
fi

# could be combined with the primary build
mvn release:clean
mvn release:prepare -DreleaseVersion="$version" -Dtag="$tag"
mvn release:perform
rm -fr ".gh-pages/doc/$tag"
cp -r target/checkout/target/reports/apidocs ".gh-pages/doc/$tag"

pushd .gh-pages

git add doc/
git commit -m "Updated for $tag" -a

echo "Release notes for $version:

$notes

"

read -e -p "Push to origin? " should_push

if [ "$should_push" != "y" ]; then
    echo "Aborting"
    exit 1
fi

git push

popd

git push
git push --tags

gh release create --target "$(git branch --show-current)" -t "$version" -n "$notes" "$tag" \
    "target/checkout/target/minfraud-$version-with-dependencies.zip" \
    "target/checkout/target/minfraud-$version-with-dependencies.zip.asc"
