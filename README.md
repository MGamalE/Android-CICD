## Android-CICD

This repo demonstrates how to work on CI/CD for Mobile Apps :iphone: using Github Actions :pill: + Firebase Distribution :tada:

# Getting Started 

We are here setup a continious integration piplines using [**Github Actions**](https://github.com/features/actions) and a continious delivery using [**Firebase Distribution**](https://firebase.google.com/docs/app-distribution) ⚡ ⚡

If you want to know a brief definition fot the two terms CI/CD 🙆‍♂️, Checkout out the qoutes :

  * [Continuous Integration](https://en.wikipedia.org/wiki/Continuous_integration)

    > Is the practice of merging all developers' working copies to a shared mainline several times a day.

 * [Continuous Delivery](https://en.wikipedia.org/wiki/Continuous_delivery)

    > Is a software engineering approach in which teams produce software in short cycles, 
    > ensuring that the software can be reliably released at any time and, when releasing the software, without doing so manually.


 ![CI/CD](https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/CI-CD-Image.png)


# Workflows 

  * 🚀 [pre_check.yaml](https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/.github/workflows/pre_check.yml) : This workflow have to check for lint, testing and static code analyzer 
  * 🚀 [build.yaml](https://github.com/MohamedGElsharkawy/Android-CICD/blob/master/.github/workflows/build.yml) : This workflow have to build and deploy to firebase distribution

## Getting Started With CI ⚡

 To get start with build CI piplines, you should use **Actions tab** or create a new **YAML** file, then you could setup your workflow, please checkout [Metadata syntax for GitHub Actions](https://docs.github.com/en/actions/creating-actions/metadata-syntax-for-github-actions) :monocle_face:
 
 ### How to create your own workflow 

1. **name** 

> Name of your workflow 

2. **on** 

> Control when the workflow will be triggerd

3. **jobs** 

> Deterimne one or more jobs / pipelines to run for the workflow, but you have to specifiy some parameters to run the job

   * job_name 

   > Pick up your own job name

   * runs_on

   > Specify your runner type

   * steps

   > Represent a squence of tasks will be exceuted for each job / pipeline, each step have a some of parameters

  * uses

   > One of step parameters, you can use it when your are trying to install enviornment or repository from marketplace 

  * run

   > One of step parameters, you can use it when your are trying to hit a command 


### CI Example :partying_face:

This workflow run as a lint checker for each pushing to master branch :rocket:

```
name: Build lint checker report

on:
  push:
    branches: [ master ]

jobs:
  lint:
    runs-on: ubuntu-latest
    steps: 
      - name: Checkout
        uses: actions/checkout@v2.3.5

      - name: Setup Java JDK
        uses: actions/setup-java@v1
        with:
          java-version: 1.8

      - name: Build Lint
        run: ./gradlew lintDebug
    
      - name: Upload Build Lint Report
        uses: actions/upload-artifact@v2.2.4
        with:
          name: report
          path: app/build/reports/lint-results-debug.html
```

1. [Checkout](https://github.com/marketplace/actions/checkout) : This action checks-out your repository under `$GITHUB_WORKSPACE`, so your workflow can access it.
2. [Setup Java JDK](https://github.com/marketplace/actions/setup-java-jdk)
3. Build Lint : Run lint report 
4. [Upload Build Lint Report](https://github.com/marketplace/actions/upload-a-build-artifact) : This uploads artifacts from your workflow allowing you to share data between jobs and store data once a workflow is complete.
