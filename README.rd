
# Rindus test
+ [Overview](#overview)
+ [Statement](#Statement)
+ [Assumptions](#assumptions)
+ [Improvements](#improvements)
    
## Overview
Online java exercise for Rindus test. An API to

## Statement
Create a small App using Java that consumes a REST API service, using SpringBoot as the base for the project is allowed.
Please use as REST service to be consumed the Fake API offered by https://jsonplaceholder.typicode.com/.
Implement as many REST Methods as possible in at least one of the resources, e.g. Post, but please call at worst two
different resources.

Extract the data from at least one resource are store it in a JSON File, additionally, you can create an XML file to
store the same data.

Please provide information about the decisions taken and how you would improve it in the future, especially considering
that this data will be pushed to another service.

## Assumptions
I have assumed the following assumptions for creating the code:

- I created a rest service for call the external API
- There is one Controller class for each created resource. Each controller is managing all the calls for this resource.
- The user is able to define generic output for the XML file overriding the property defined in application.properties,
before starting te application.
- Every Controller has a Test class that check the behaviour for the different verbs defined. Also WriteFileService has
a specific test to test the behaviour.

Services:
- Additionally I created 2 services to manage 2 different tasks.
  - ExtractDataService. It can be used to serialize a JSON from an existing object. This service has been created in order to
  let create the serialization. I considered to parse the object at the same time I'm getting the info from the external API.
  It avoids managing external processes for it.
  - WriteFileService. This service is the responsible of writing the info extracted from one external service into a XML file.
  The XML file location could be set up modifying the property "xml.output.path" in application.properties.

## Improvements
- I could consider to parse/unparse objects using ExternalDataService as an improvement. Separate it, could let us create a checker
to controlls possible errors on the the seralization process.
- We could think about managing custom exceptions for informing about possible errors. I considered we are retrieving the correct
info from the external API, but for ensuring it, managing the exceptions would be a really good improvement.
- The location of the created XML file is configurable but, it remains static during the whole life of the API.
We could consider defining it as a external parameter that the user can override at runtime.