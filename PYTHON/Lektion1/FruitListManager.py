#Create and manipulate a list: Write a program to create a list of your favorite fruits. 
#Add at least two more fruits to the list using append. 
# Then remove one fruit and replace it with a different one. 
# Finally, print the modified list.
#Expected Output: A list of fruits, showing additions and replacements.

def fruitListManager ():
    fruitList = ["banana", "apple", "orange", "kiwi", "melon"]
    print("The initial list of fruit: \n" + str(fruitList))
    print()

    #add fruits with append
    fruitList.append("pineapple")
    fruitList.append("plum")

    print("After adding two new fruit, the list is now: \n" + str(fruitList))
    print()

    #remove a fruit and replace it
    fruitList.remove("melon")
    fruitList.insert(4, "strawberry")

    print("After removing melon and replacing it, the list is now: \n" + str(fruitList))
    print()

fruitListManager()