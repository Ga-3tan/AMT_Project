<div id="top"></div>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]

<!-- PROJECT LOGO -->
<br />

<div align="center">
  <a href="https://github.com/github_username/repo_name">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>


<h3 align="center">AMTech Project</h3>

  <p align="center">
    The AMTech Project is a school practical project that aims to extend our skills on web app developpment and team work.
    <br />
    <a href="https://github.com/Ga-3tan/AMT_Project/wiki"><strong>Explore the wiki »</strong></a>
    <br />
  </p>

</div>



<!-- TABLE OF CONTENTS -->

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>




<!-- ABOUT THE PROJECT -->

## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

Here's a blank template to get started: To avoid retyping too much info. Do a search and replace with your text editor for the following: `github_username`, `repo_name`, `twitter_handle`, `linkedin_username`, `email`, `email_client`, `project_title`, `project_description`

<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

* [Spring](https://spring.io/)
* [Couchease](https://couchbase.com/)
* [AWS](https://https://aws.amazon.com/)
* [FreeMarker](https://https://freemarker.apache.org/)

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- GETTING STARTED -->

## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

* Java 11
* Maven 3.6.0

<!-- CONTRIBUTING -->

## Contributing

1. Clone the repo

   ```sh
   git clone https://github.com/Ga-3tan/AMT_Project.git
   ```

### Please use Git Flow :
#### Introduction
Gitflow is an alternative Git branching model that involves the use of feature branches and multiple primary branches.

The idea of Gitflow is to use two branches to record the history of the project instead of one main branch. The main branch stores the official release history, and the develop branch serves as an integration branch for features.

Under this model, developers create a branch for each new feature and delay merging it to the main trunk branch until the feature is complete. When merging in main branch, all commits are tagged with a version number.

![Gitflow Model](./images/git-flow-model.svg)

#### Initialization
After installing GitFlow, the first step is to create a develop branch. A developer can simply create an empty branch and push it to the server using following commands:
   ```sh
   git branch develop
   git push -u origin develop
   ```
_Note: As mentioned above, this branch is used for the history of the project. Also, one can set freely a different branch name._

Then, using ```git flow init``` will set this branch as the develop branch in Gitflow model.

![Gitflow init](./images/git-flow-init.png)

_Note: As mentioned above, this branch is used for the history of the project. Also, one can set freely a different branch name._

#### New feature
_Reminder: Each new feature should reside in its own branch. feature branches use develop as their parent branch. When a feature is complete, it gets merged back into develop branch. Features should never interact directly with main._

* Create a new feature branch
   ```sh
   git flow feature start feature_branch
   ```
  The developer can now continue to work (with Git) normally.

* Finish a feature branch
   ```sh
   git flow feature finish feature_branch
   ```
  The ```feature_branch``` is now merged to develop branch.

#### Release branch
Once a feature is ready for a release, a release branch is forked off of the develop branch by using the following command:

![Gitflow init](./images/git-flow-release.png)
Once the release is ready to ship, it will get merged it into main and develop, then the release branch will be deleted.

_Note: It’s important to merge back into develop because critical updates may have been added to the release branch and they need to be accessible to new features._

To finish a release branch, use the following methods:
   ```sh
   git flow release finish '0.1.0'
   ```

#### Hot fix branch
Hotfix branches are a lot like release branches and feature branches except they're based on main instead of develop. __This is the only branch that should fork directly off of main.__

As soon as the fix is complete, it should be merged into both main and develop (or the current release branch), and main should be tagged with an updated version number.

A hotfix branch can be created using the following methods:
   ```sh
   git flow hotfix start hotfix_branch
   ```
Then merge the branch to main and develop branch as usual:
   ```sh
    git checkout main
    git merge hotfix_branch
    git checkout develop
    git merge hotfix_branch
    git branch -D hotfix_branch
   ```
Once done, use the following command to finish the hot fix branch:
   ```sh
   git flow hotfix finish hotfix_branch
   ```
---


2. Fork the Project
3. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
4. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
5. Push to the Branch (`git push origin feature/AmazingFeature`)
6. Open a Pull Request

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- LICENSE -->

## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTACT -->

## Contact

Project Link: [https://github.com/Ga-3tan/AMT_Project](https://github.com/Ga-3tan/AMT_Project)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[contributors-shield]: https://img.shields.io/github/contributors/Ga-3tan/AMT_Project.svg?style=for-the-badge
[contributors-url]: https://github.com/Ga-3tan/AMT_Project/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/Ga-3tan/AMT_Project.svg?style=for-the-badge
[forks-url]: https://github.com/Ga-3tan/AMT_Project/network/members
[stars-shield]: https://img.shields.io/github/stars/Ga-3tan/AMT_Project.svg?style=for-the-badge
[stars-url]: https://github.com/Ga-3tan/AMT_Project/stargazers
[issues-shield]: https://img.shields.io/github/issues/Ga-3tan/AMT_Project.svg?style=for-the-badge
[issues-url]: https://github.com/Ga-3tan/AMT_Project/issues
[license-shield]: https://img.shields.io/github/license/Ga-3tan/AMT_Project.svg?style=for-the-badge
[license-url]: https://github.com/Ga-3tan/AMT_Project/blob/master/LICENSE
[product-screenshot]: images/screenshot.png
## License
[MIT](https://choosealicense.com/licenses/mit/)
