Feature: Crud items Todo.ly

  Scenario: as a user I want to create, update, delete of the items successfully

    Given I have access to Todo.ly API
    And I get the token to use in the next request
    When I send a POST request /api/items.json with body
    """
      {
        "Content":"Taller 02 item"
      }
    """
    Then the response code should be 200
    And the response body should be
    """
      {
          "Id": IGNORE,
          "Content": "Taller 02 item",
          "ItemType": 1,
          "Checked": false,
          "ProjectId": null,
          "ParentId": null,
          "Path": "",
          "Collapsed": false,
          "DateString": null,
          "DateStringPriority": 0,
          "DueDate": "",
          "Recurrence": null,
          "ItemOrder": null,
          "Priority": IGNORE,
          "LastSyncedDateTime": "IGNORE",
          "Children": [

          ],
          "DueDateTime": null,
          "CreatedDate": "IGNORE",
          "LastCheckedDate": null,
          "LastUpdatedDate": "IGNORE",
          "Deleted": false,
          "Notes": "",
          "InHistory": false,
          "SyncClientCreationId": null,
          "DueTimeSpecified": true,
          "OwnerId": IGNORE
      }
    """
    And I save the "Id" in the variable: "ID_ITEM"