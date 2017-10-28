# Peer-to-Peer lending on Blockchain

![Image](https://github.com/papajijaat/HashHacks2.0-methOD/blob/master/Future_Prospects/double_spend.png?raw=true)

## Ascent of Money
Money is power.<br/>
Nobody knew this better than the kings of the ancient world. That’s why they gave themselves an absolute monopoly on minting moolah.<br/>
They turned shiny metal into coins, paid their soldiers and their soldiers bought things at local stores. The king then sent their soldiers to the merchants with a simple message:<br/>
“Pay your taxes in this coin or we’ll kill you.”<br/>

## Preamble
Banks are centralised institutions that, among other things, offer credit to individuals, groups and corporations. Historically, banks were a nexus of borrowers and lenders, matching the supply of liquidity to the demand for credit. The primary role of a bank was risk management in the form of the due diligence required to assess if a potential borrower was a worthwhile investment. With the advent of nation-state money printing and fractional-reserve banking, whereby money is lent into existence and losses are publicly subsidized by inflation and bailouts, the traditional care of risk management for loans has all but been obiliterated.<br />
Peer-to-peer (P2P) lending has emerged as a means to decentralise the oligopolistic hold that banks possess in every category for lending. While P2P lending is still in early days, regulatory pressure is mounting making P2P lending services using fiat dollars especially vulnerable to the legacy financial/political order.<br />

## Loan Contracts
![Image](https://github.com/papajijaat/HashHacks2.0-methOD/blob/master/Future_Prospects/alicebob.png?raw=true)

Step 1: Bob creates a loan contract<br/>
Step 2: Alice accepts the loan contract<br/>
Step 3: Bob accepts Alice's offer and sends to the contract to the arbiter<br/>
Step 4: Funds are transferred; repayment-receipt tracking begins (last signed receipt)<br/>

## Risk Management

### Part 1: Credit Ratings and Collateral

The process above has thus far described how the loan contract is created and processed for auditing purposes by the arbiter. However, it has not dealt with how loan contracts will be protected from fraud by bad actors on OpenBazaar. Traditionally, there are two major ways to manage risk for potential loans:

Assess the borrower's credit rating

Credit Rating

An estimate of the ability of a person or organization to fulfill their financial commitments, based on previous dealings.
As a credit rating often involves an individual disclosing their income and previous financial dealings to the creditor and other third parties


### Part 2: Web of Trust

The cumulative rating on the web of trust is another measure of risk other parties can consider before making a trade with you. Moreover, the network of peers and their corresponding rating also is a measure of the quantity and quality of that rating. Lastly, web of trust ratings aren't predicated on a trade, allowing existing trust networks to be translated into the system. The web of trust platform can be expanded to further differentiate between free and trade-based ratings to signal more information to other parties.

In the context of peer-to-peer lending, the web of trust can act as a form of credit rating.

### Part 3: Web of Credit

Taking the web of trust concept one step further, OpenBazaar can facilitate peers extending lines of credit to each other. If there is a successful trade between Alice and Bob, Alice may choose to extend a 5 mBTC line of credit to Bob. Bob can borrow this money at any time according to the prescribed conditions set by Alice. The funds can be kept in a 2-of-3 multisignature address, using an arbiter as a third signature. This line of credit can be publicly disclosed and audited by other peers. The aggregate of a pseudonym's line of credit becomes a powerful and informative risk signal for other peers, with risk being inversely proportional to the sum total line of credit.

The line of credit can be considered as collateral by a potential creditor, knowing that a pseudonym's line of credit can be called upon to satisfy a renumberation of second loan. For individual extending lines of credit, they have an opportunity to invest in successful and well-regarded trade partners.


### Part 4: Collateral and Distributed Collateralized Trusts

What if a user like Bob is unable to provide sufficient collateral for the loan due to either: 1) his wealth status living in a developing country, or 2) providing collateral in the traditional sense would reveal his pseudonym's identity?

One possible solution is for Bob to purchase collateral from a distributed collateralized trust or dCT. A dCT is a collection of users that pool a certain value of funds in essentially a term deposit. This term deposit serves as collateral for a loan in the event that the borrower defaults. In return for this risk, the borrower pays each member of the trust a fee that makes up the effective interest rate of the term deposit. 

#### dCTs however, confer a number of advantages:

Overall level of risk is spread <br />
Multiple layers of due diligence <br />
Both the creditor and the dCT will need to evaluate the profitability of lending to Bob, decreasing the chances of spurious lending <br />
The dCT can also examine the track record of the creditor, Alice, for their success in picking borrowers <br />
The purpose of the loan can be interrogated, and if possible, funds can incrementally be released <br />
Promotes liquidity <br />
The creditor has, in effect, insurance in lending <br />
The level of risk that is an obstacle for the lender is now lower than the level of risk for each member of the dCT, making relatively capital-intensive loans possible <br />
