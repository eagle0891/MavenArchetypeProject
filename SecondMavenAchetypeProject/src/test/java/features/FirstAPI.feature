Feature: API Test

  Scenario Outline: firstStarWarsTest
    Given request contains '<contentType>' and Star Wars films query, and request is sent to '<uri>', and response returns status <statusCode> and body '<responseBody>' matches '<expectedResponse>'
#    When request is sent to '<uri>'
#    Then response returns status '<statusCode>' and body '<responseBody>'
    Examples:
      | contentType      | requestBody                                                          | uri                       | statusCode | responseBody                 | expectedResponse |
      | application/json | {"query":"{\n  allFilms {\n    films {\n      title\n    }\n  }\n}"} | /.netlify/functions/index | 200        | data.allFilms.films[0].title | A New Hope       |