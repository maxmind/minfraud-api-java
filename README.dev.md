# Preparing your environment for a release
  - Ensure you have access to publish to the repository on
    [Sonatype](https://oss.sonatype.org).
    - See the section about Sonatype access.
  - You need a GPG secret key. You need to publish it as well.
    - See the section about setting up GPG.
  - Ensure the SSH key you use on GitHub.com is available.
    - e.g., `~/.ssh/id_rsa`.
  - Ensure an appropriate `~/.gitconfig` is set up.
    - The release process generates commits.
  - Ensure you have the necessary dependencies available:
    - e.g., `apt-get install maven openjdk-8-jdk git-core gpg`
  - Ensure [hub](https://github.com/github.com/hub) is set up and in your
    `PATH`.
    - An easy way to do this is get a release tarball and run `./install`.


## Setting up Sonatype access
To get this access, make an account on the [Sonatype JIRA issue
tracker](https://issues.sonatype.org/) and make an issue asking for access
[like so](https://issues.sonatype.org/browse/OSSRH-34414).

Ensure you inform MaxMind operations about your new access.

Put this information into `~/.m2/settings.xml` (Maven settings). Your
`settings.xml` should look something like (replace USERNAME and SECRET):

    <settings>
      <servers>
        <server>
          <id>ossrh</id>
          <username>USERNAME</username>
          <password>SECRET</password>
        </server>
        <server>
          <id>sonatype-nexus-snapshots</id>
          <username>USERNAME</username>
          <password>SECRET</password>
        </server>
        <server>
          <id>sonatype-nexus-staging</id>
          <username>USERNAME</username>
          <password>SECRET</password>
        </server>
        <server>
          <id>github-project-site</id>
          <username>git</username>
        </server>
      </servers>
    </settings>

Some links about Sonatype:

  * http://central.sonatype.org/pages/ossrh-guide.html
  * http://central.sonatype.org/pages/releasing-the-deployment.html
  * http://central.sonatype.org/pages/apache-maven.html


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
  - Ensure you have a clean checkout of `master` and that the subdirectory
    `.gh-pages` either does not exist or is a clean checkout.
    - We'll be generating commits.
  - Set a version and a date in `CHANGELOG.md` and commit that.
    - It gets used in the release process.
  - Run `./dev-bin/release.sh`
  - This will prompt you several times. Generally you need to say `y` or
    `n`.
  - You'll be prompted for your ssh key password, GPG key password, and
    GitHub.com username and password several times depending on your
    workspace.
  - You may be prompted about "what is the next development version?". Hit
    enter to use the default which should be fine.
  - You may be prompted about "The following dependencies in Dependencies
    have newer versions". See the section about updating dependencies if so.
  - If you get HTTP 401 errors from Sonatype, you probably don't have a
    correct `settings.xml`. Refer to the Sonatype section.
    - If this happened, there are most likely pushed commits on GitHub.com.
      You can potentially rebase and drop the commits and force push and
      start over (you might want to talk about this before doing it). Note
      you'll need to delete the release tag too:
      `git push origin :refs/tags/vx.y.z && git tag -d vx.y.z`.
  - If you get to this point, then a release is on GitHub.com and in the
    staging repository on [Sonatype](https://oss.sonatype.org).
  - Go to [Sonatype](https://oss.sonatype.org) and log in.
  - On the left hand side, choose Staging repositories.
  - At the bottom you should see your staged release. It will be something
    like "commmaxmind-xxxx". Click on that.
  - Near the top of the page there is a "Close" button. Click it.
    - You have to close a staging release before you can release it.
  - Wait around 30 seconds. Click Refresh near the top of the page while
    you wait. Eventually you should see the "Activity" line in the info
    about your repository say something like it is complete.
  - Click Release near the top.
  - You're done!
  - If you want to check things over, look at the commits on GitHub.com,
    including to the `gh-pages` branch and release tags, and do an artifact
    search on [Sonatype](https://oss.sonatype.org) to see the version is as
    you expect.


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
