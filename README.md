# stockticker
Simple command line stock ticker app using Yahoo Finance API.

## build
Linux:
To build execute ./gradlew clean build

## execute
After building distributions can be found under build/distributions. 

Linux:
1. untar the distribution
1. cd stockticker*/bin
1. execute optionally supplying stock symbol list at command line e.g.

```
  ./stockticker ge ibm msft
```

press Enter during execution to see menu of options

Sample output:

```
$ ./stockticker ge ibm msft
10:29:25 ge 24.700
10:29:25 ibm 146.020
10:29:25 msft 73.500

10:29:35 ge 24.700
10:29:35 ibm 146.030
10:29:35 msft 73.510
```
