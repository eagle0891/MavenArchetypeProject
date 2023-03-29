Feature: API Test

  Scenario Outline: firstStarWarsTest
    Given request contains '<contentType>' and '<requestBody>', and request is sent to '<uri>', and response returns status '<statusCode>' and body '<responseBody>' matches '<expectedResponse>'
#    When request is sent to '<uri>'
#    Then response returns status '<statusCode>' and body '<responseBody>'
    Examples:
      | contentType      | requestBody           | uri                       | statusCode | responseBody                 | expectedResponse        |
      | application/json | allStarWarsFilmsQuery | /.netlify/functions/index | 200        | data.allFilms.films[0].title | A New Hope              |
      | application/json | allStarWarsFilmsQuery | /.netlify/functions/index | 200        | data.allFilms.films[1].title | The Empire Strikes Back |
      | application/json | allStarWarsFilmsQuery | /.netlify/functions/index | 200        | data.allFilms.films[2].title | Return of the Jedi      |
      | application/json | allStarWarsFilmsQuery | /.netlify/functions/index | 200        | data.allFilms.films[3].title | The Phantom Menace      |
      | application/json | allStarWarsFilmsQuery | /.netlify/functions/index | 200        | data.allFilms.films[4].title | Attack of the Clones    |
      | application/json | allStarWarsFilmsQuery | /.netlify/functions/index | 200        | data.allFilms.films[5].title | Revenge of the Sith     |

  Scenario Outline: firstHasuraTest
    Given request contains '<contentType>', '<headerTitle>', '<headerValue>', '<requestBody>', and request is sent to '<uri>', and response returns status '<statusCode>' and body '<responseBody>' matches '<expectedResponse>'
    Examples:
      | contentType      | headerTitle           | headerValue                                                      | requestBody     | uri         | statusCode | responseBody                    | expectedResponse |
      | application/json | x-hasura-admin-secret | 3m1kpYOAi6QkJsjCC1qpzHe0KTd1cDVffdlkqKq3DMrFHnpXxAAtpMNym7ZNHzKk | albumsListQuery | /v1/graphql | 200        | data.Album.Tracks[0].TrackId[0] | 1                |