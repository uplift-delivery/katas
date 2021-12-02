Documentation for the Python version
====================================

First you will need to install texttest

    $> pip install texttest

Unfortunately it only works with Python 2, and not Python 3

Navigate on the command line to the same folder as this README file. Then you can execute the tests with this command:

    $> texttest -a gr -b all -c $PWD -d $PWD/texttests

Note that $PWD should print the full path of the current working directory. On some unix versions this doesn't work, so be prepared to write out the full path.

There are instructions for managing your test cases in the top-level README file. For the python version these are the values for the environment variables:

    $>set TEXTTEST_HOME = $PWD/texttests
    $>set TEXTTEST_TMP = ~/.texttest/tmp
