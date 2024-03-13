[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]

# mdtohtml-cli

Spring Shell project to generate single command CLI.
The CLI is used to convert the `README.md` file in [this other repo](https://github.com/spring-office-hours/resources-learning-spring)
to a set of bookmarks that can be imported into a browser.

The objective is to use a GraalVM generated native-image as part of
a GitHub Action that would update the bookmarks file on each push to the repo.

## Prerequisites
- Java 21

## Quick Start

```bash
./mvnw spring-boot:run
```

## Related Videos
- [Spring Office Hours S3E8](https://www.youtube.com/live/W0u9XaCyWPo?si=Tc-nC8w9S3UsM44o)

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[forks-shield]: https://img.shields.io/github/forks/spring-office-hours/mdtohtml-cli.svg?style=for-the-badge
[forks-url]: https://github.com/spring-office-hours/mdtohtml-cli/forks
[stars-shield]: https://img.shields.io/github/stars/spring-office-hours/mdtohtml-cli.svg?style=for-the-badge
[stars-url]: https://github.com/spring-office-hours/mdtohtml-cli/stargazers
[issues-shield]: https://img.shields.io/github/issues/spring-office-hours/mdtohtml-cli.svg?style=for-the-badge
[issues-url]: https://github.com/spring-office-hours/mdtohtml-cli/issues