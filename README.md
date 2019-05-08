
# Bulk CLA Permissions Checker
Submit a list of ISBNs and ISSNs and get the CLA permissions by licence.

## Installation and Setup
 * Download the latest exe from the Assets part of the releases area [here](https://github.com/alcole/bulk-cla-permissions/releases).
 * Sign up for an access key (AKA Ocp-Apim-Subscription-Key) for the CLA Check Permissions API via the API portal [here](https://apiportal.cla.co.uk/products).

## How to Use
You will need to provide 3 files

 1. key.txt - save your Ocp-Apim key from the apiportal in this file
 2. licenceCode.txt - save the licence code here (see below for list of available codes)
 3. toCheck.csv - a one column csv file (no header) of ISBN/ISSN to run through the checker
 
These files must be in the same folder as the downloaded exe file.

To run double click the exe, a progress box will appear. When this closes the process has finished and there will be 2 new files in the `out` folder, a set of results and a summary log of the process.


## Licence Codes

| Code | Licence Sector                        |
|:-----|:--------------------------------------|
| 132  | Further Education                     |
| 134  | Business                              |
| 136  | Higher Education                      |
| 132  | Further Education                     |
| 137  | Law                                   |
| 140  | Extended Multinational                |
| 141  | Pharmaceutical                        |
| 143  | Schools                               |
| 154  | Public Sector                         |
| 230  | Extended Pharmaceutical Multinational |
| 232  | Media Monitoring                      |
| 234  | Multinational                         |
| 235  | Pharmaceutical Multinational          |

* * *

## Notes
Although I am an employee of CLA this is an independent project and is offered without guarantee.

 * * *
Please share ideas or submit pull requests, and please let me know if this tool has helped you.

 * [identifiertools.com ](https://www.identifiertools.com/)
 * [My profile on GitHub User](https://ghuser.io/alcole/)
 * [My profile on HackerRank](https://www.hackerrank.com/alcole)
 * [Twitter](https://twitter.com/AlexJCole)
 * [LinkedIn](https://www.linkedin.com/in/alexcole01/)

