Feature: API Test

  Scenario Outline: firstStarWarsTest
    Given request contains '<contentType>' and '<requestBody>', and request is sent to '<uri>', and response returns status '<statusCode>' and body '<responseBody>'
#    When request is sent to '<uri>'
#    Then response returns status '<statusCode>' and body '<responseBody>'
  Examples:
    | contentType      | requestBody                                                          | uri                       | statusCode | responseBody                 |
    | application/json | {"query":"{\n  allFilms {\n    films {\n      title\n    }\n  }\n}"} | /.netlify/functions/index | 200        | data.allFilms.films[0].title |