## A2 Participants:
km222ug(Kenan Maslan), cj223af(Charbel Jbeilly)

## A2 Deliverables:

[design.md](design.md) contains the prescribed architectural design of the application.

[testreport.md](testreport.md) contains the testcase conducted on the final commit that changed code.

## A2 RESUBMISSION (PLEASE READ):

We got 4 codiing/design complaints on our previous submission that we have fixed. The four were:

* Encapsulation problems
* Hidden dependencies
* Model responsibility in controller (seems as a lot of code in e.g. delete item should be in the model?)
* Crash on run - (test case 2.1 does not work)

We fixed encapsulation by moving code and making sure that everything is protected. In that way, we can return the object as a read only

We also fixed hidden dependencies everywhere that were int values that needs to be the same between controller and model.
Now, the model asks for the object to edit (example member, finds it in the list, and then edits it.)

Model responsibility in controller we fixed by moving most of the code to memberlist, that has to do with business logic.
Also, model cant be put in a bad state, if you force change an email without checking first for example, it wont work!

Crash on run was a bug that made it so that you could not use . in your input strings. Now it works!

## A2 findBugs removed
After careful consideration, we removed a checkbugs violation affecting Contract.java.

Our reasoning is that Contract does NOT store the real member, it stores a copy of member

So even if we edit the member/expose the member, it does not matter.

This is allowed by hobbe, we found this bug and info about it in slack.

Code block:
    <pre><code>lines: 17-85 
    new model.Contract(Member, Member, Item, int, int) may expose internal 
        representation by storing an externally mutable object into 
        Contract.borrower 
    May expose internal representation by incorporating reference to mutable 
    object</code></pre>

## A2 Basic tutorial to use program
Everything is pretty straight forward in the program, you just press numbers to navigate through the list.

However, item listing is not its own function, rather, it is included in the verbose listing of members.

Every item a member owns is shown under the member.

The same applies for contracts.


