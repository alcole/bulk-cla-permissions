# Bulk CLA Permissions Checker
Submit a list of ISBNs and ISSNs and get the permissions by licence.
## Installation and Setup
Download the latest exe from the releases area [here](https://github.com/alcole/jcla-permissions-working/releases).
Sign up for an access key for the CLA Check Permissions API via the API portal [here](https://apiportal.cla.co.uk/products).
## How to Use
You will need to provide 3 files

 1. key.txt - for the Ocp-Apim key from the apiportal
 2. licence.txt - store the licence code here (see here for list of available codes)
 3. toCheck.csv - csv files of ISBN/ISSN to run through checker
 
These files must be in the same  folder as the downloaded exe file

To run click the exe, a progress box will appear. When this closes the process has finished, there will be 2 new files in the out folder, a set of results and a summary log of the process.

Read more about the ISNI project [here](http://www.isni.org/) or on [Wikipedia](https://en.wikipedia.org/wiki/International_Standard_Name_Identifier).

## Licence Codes
|Code| Licence Sector |
|--|--|
|  132|Further Education  |
|  134|Business  |
|  136|Higher Education  |
|  132|Further Education  |
|  137|Law  |
|  140|Extended Multinational  |
|  141|Pharmaceutical  |
|  143|Schools  |
|  154|Public Sector  |
|  230|Extended Pharmaceutical Multinational  |
|  232|Media Monitoring  |
|  234|Multinational  |
|  235|Pharmaceutical Multinational  |

## Notes
Although I am an employee of CLA this is an independent project and is offered without guarantee.
