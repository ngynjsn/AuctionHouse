# Auction House Simulator
### By: Jason Nguyen

**Features of the Application:**

- In this application, users will be allowed to put items up for sale like if
it were an auction house. Items can be added and the auction will begin with the first
item on the list. Users get to choose *starting price*, *bidding price*, and *buy out offers* for bids. If the bid goes past the buy out offer, the item goes to the
last person who made the bid.

**Users and Interest:**

- This application will be used for people who have items they would like to sell. Instead
of having to choose who to sell it to, an auction house would be fun and maximizes the seller's profit. 
This project is of interest to me because it'll challenge me to set up the functionalities of the application
on my own.

#### User Stories:

- As a user, I want to be able to add items into my auctioning list.
- As a user, I want to be able to remove items from my auctioning list.
- As a user, I want to be able to view my items on the auctioning list.
- As a user, I want to be able to set the bidding functionalities for each item.
- As a user, I want to be able to begin an auction house.
- As a user, I want to be able to remember who bought a specific item.
- As a user, I want to be able to see the total profit made in that sitting.
- As a user, I want to be able to save my items in the auctioning list in case I don't
to sell the items yet.
- As a user, when I start the application, I want to be given the option to load any pre-loaded,
auctioning list.

**Phase 4: Task 2**
- Option 1: Test and design a class in your model package that is robust.
You must have at least one method that throws a checked exception.
You must have one test for the case where the exception is expected and another where the exception is not expected.

- Created an exception called EmptyListException which is thrown whenever a user is trying
to retrieve an item from an empty list. This exception is thrown from model.AuctioningList in the method
getFirstItem(). It is used in ui.AuctionHouseConsole, ui.AuctionHouseFrame, and ui.AuctionHouseGUI for the purposes of:
ending the auction house when the list is empty and failing to start the auction house when attemping to start
the auction house with no items.

**Phase 4: Task 3**
- I feel that there isn't much to improve since I feel that there are enough classes to follow the Single Responsibility
Principle and there isn't too many classes that clutters the project.
