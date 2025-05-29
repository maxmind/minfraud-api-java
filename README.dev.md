# Preparing your environment for a release

- Ensure you have access to publish to the repository on
  [Central Portal](https://central.sonatype.org/).
  - See the section about Central Portal access.
- You need a GPG secret key. You need to publish it as well.
  - See the section about setting up GPG.
- Ensure the SSH key you use on GitHub.com is available.
  - e.g., `~/.ssh/id_rsa`.
- Ensure an appropriate `~/.gitconfig` is set up.
  - The release process generates commits.
- Ensure you have the necessary dependencies available:
  - e.g., `apt-get install maven default-jdk git-core`
- Ensure [gh](https://github.com/cli/cli) is set up and in your
  `PATH`.
  - An easy way to do this is get a release tarball and run `./install`.

## Setting up Central Portal access

To get this access, first create a Central Portal account. You will then need
access to our namespace, but we have not added anyone since switching to
Central Portal. Previously you would need to make an account on the [Sonatype
JIRA issue tracker](https://issues.sonatype.org/) and make an issue asking for
access [like so](https://issues.sonatype.org/browse/OSSRH-34414).

Ensure you inform MaxMind operations about your new access.

Configure your `~/.m2/settings.xml` file for releasing to Central Portal. See
[these instructions](https://central.sonatype.org/publish/publish-portal-maven/#credentials).

Some links about Central Portal:

* [Maven Central Repository homepage](https://central.sonatype.com/). You can
  sign-in from here.
* [Publishing guide](https://central.sonatype.org/publish/publish-portal-maven/)

## Setting up GPG

You need a key. It is fine to create/use your own, but you'll probably want
one with your MaxMind email address.

If you need to generate a key: `gpg --gen-key`.

If you have one and need to export/import it:

    gpg --export-secret-keys --armor > secretkey.gpg
    gpg --import secretkey.gpg
    gpg --edit-key <key ID>

and enter `trust` and choose ultimate.

Make sure the key shows up in `gpg --list-secret-keys`.

Make sure you publish it to a keyserver. See
[here](http://central.sonatype.org/pages/working-with-pgp-signatures.html)
for more info about that and the process in general.

### gpg "inappropriate ioctl" errors

You only really need to do this if you see "inappropriate ioctl" errors,
but it shouldn't hurt to proactively do this.

Add this to ~/.gnupg/gpg.conf:

    use-agent
    pinentry-mode loopback

Add this to ~/.gnupg/gpg-agent.conf:

    allow-loopback-pinentry

# Releasing

## Steps

- Ensure you can run `mvn test` and `mvn package` successfully. Run
  `mvn clean` after.
- Create a release branch off `main`. Ensure you have a clean checkout and that
  the subdirectory `.gh-pages` either does not exist or is a clean checkout.
  - We'll be generating commits.
  - When the release is complete, you should deliver the release PR for review.
- Review open issues and PRs to see if any can easily be fixed, closed, or
  merged.
- Review `CHANGELOG.md` for completeness and correctness.
- Set a version and a date in `CHANGELOG.md` and commit that.
  - It gets used in the release process.
- Bump copyright year in `README.md` if appropriate.
  - You don't need to update the version. `./dev-bin/release.sh` does this.
- Run `./dev-bin/release.sh`
  - This will package the release, update the gh-pages branch, bump the
    version to the next development release, upload the release to GitHub
    and tag it, and upload to Sonatype.
- This will prompt you several times. Generally you need to say `y` or `n`.
- You may be prompted for your GitHub.com username and password several
  times depending on your workspace.
- You may be prompted about "The following dependencies in Dependencies
  have newer versions". See the section about updating dependencies if so.
- If you get HTTP 401 errors from Central Portal, you probably don't have a
  correct `settings.xml`. Refer to the Central Portal section.
- If you get to this point, then a release is on GitHub.com and Maven
  Central.
- You're done!
- If you want to check things over, look at the commits on GitHub.com,
  including to the `gh-pages` branch and release tags, and do an artifact
  search on [Maven Central](https://central.sonatype.com/) to see the version
  is as you expect. It may take a few minutes for new releases to show up
  on Maven Central.

## Updating dependencies

Review the versions and look at what changed in their changelogs. If you
think it is appropriate to update the dependencies, stop the release
process (say `n` or ctrl-c out).

To update them:

- Make a branch
- Update `pom.xml` to have the new versions you want
- Run `mvn test` and fix any errors
- Push and ensure Travis completes successfully
- Merge

If you did this in the middle of releasing, you'll have to start that
process over.
