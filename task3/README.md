Task number 3. For those who have already sent # 1 and # 2.

In a statically typed language (choice of Java or C #), implement a script (in the form of a jar file or an executable assembly) that generates fake user data (name, address, phone number).

When launched by command line parameters (arguments of the main or Main method) 2 or 3 parameters are passed (the third one is 0 by default).

The first parameter sets the region and language (you need to support the following three: en_US, ru_RU, be_BY - addresses in the United States in English, addresses in the Russian Federation in Russian, and addresses in Belarus in Belarusian, respectively).

The second parameter (natural number) sets the number of generated records (there should be no artificial restrictions in the code, but you need to focus on somewhere around 10,000,000 records, if a lot is specified, the probability of duplicates can be high).

The third parameter (non-negative real number) sets the average number of errors per 1 record (i.e. 0.5 means that there will be an average of one error per two records, and 50 means 50 errors per one record).

The running time of the script should not exceed 1 minute (much less is better) per 1,000,000 records (excluding output to the terminal, of course).

Records are displayed one per line, fields (name, address, phone number) are separated by semicolons.

An error is one of three actions, deleting a random character, adding a random alphanumeric character (for the corresponding language), rearranging adjacent characters in a record (all types of errors should be generated with equal probability, i.e. deletions and additions should be approximately the same, Thus, with a very large number of errors, entries should not turn into empty lines and should not infinitely bloat).

The data must not be real, but must be plausible (adequate indexes, normal names and surnames, in Belarus not everyone lives in Minsk, etc.)
