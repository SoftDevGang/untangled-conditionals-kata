# Untangled Conditionals Kata

[![Build Status](https://travis-ci.org/tomphp/untangled-conditionals-kata.svg?branch=master)](https://travis-ci.org/tomphp/untangled-conditionals-kata)

This repository contains a simple coding kata.

Category: **refactoring**<br>
Skill Level: **beginner**<br>
Time: **~30 mins**

This repository contains the starting code.
The logic is contained in a single method, `Pipeline.run()`.
This method contains a number of embedded `if` statements.
Your goal is to refactor the code to a more understandable and desireable state.

Remember, keep the tests passing while you refactor!

## Approach

Below you can find some resources demonstrating the approach that this kata was designed to teach.
However, I recommend you try the kata yourself first, and then review the resources.

* Blog Post: [Refactoring — Untangling Conditionals](https://cloudnative.ly/refactoring-untangling-conditionals-cc5693b8ec3c).
* Video: [![Untangled Conditionals Kata](https://img.youtube.com/vi/NWgY-0Qu4S4/0.jpg)](http://www.youtube.com/watch?v=NWgY-0Qu4S4)

## Requirements

We are creating a spike Pipeline class which controls tour build pipeline.
We will have dependencies for `Config, Email, Logging and Project information` later.

* If the project has tests we run tests
  * we log test result (success or failure)
* if there are no tests we log that there are no tests and proceed

* if tests passed (or no tests) we deploy the project.
  * we log deploy result (success or failure)

* if config is set to send emails, we send email report 
  * when test and deploy passed we email a report of that
  * when deploy failed we email a report that
  * when test failed we email a report that
  * else we log that email is disabled.
