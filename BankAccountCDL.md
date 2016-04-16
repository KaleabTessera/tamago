# Introduction #

The most present example in the design by contract is the bank account example.


# Details #

The functionalities present in a bank account is:
  * credit: increase the balance of the account
  * withdraw: decrease the balance of the account
  * init: fix the initial overdraw

The concrete syntax of this service **BankAccount.cdl** can be written as follow:
```
module tamago.bankaccount;

service BankAccount  {
        property read int balance;
        property read int overdraw;

        invariant (#overdraw >= 0);
        
        method void init(int autorizedOverdraw) {
                id init;
                pre (400 >= autorizedOverdraw) && (autorizedOverdraw >= 0);
                post (#balance == 0) && (#overdraw == autorizedOverdraw);
        }

        method void credit(int amount) {
      id credit;
      pre (amount > 0) fail "amount must be strictly positive";
                post (#balance == (#balance@pre + amount))
                 && (#overdraw == #overdraw@pre);
        }
        
        method void withdraw(int amount) {
                id withdraw;
                pre (amount > 0) fail "not enough money";
                post (#balance == (#balance@pre - amount))
                &&  (#overdraw == #overdraw@pre);
        }
        
   behavior {
                default state ninit;
                states {
                        state ninit {
                                allow init;
                        }
                        state alive {
                                allow credit;
                                allow withdraw;
                        }
                        state blocked {
                                allow credit;
                        }
                }
                transitions {
                        transition from ninit to alive with init;
                        transition from alive to blocked with withdraw when ((#balance + #overdraw) < 0);
                        transition from blocked to alive with credit when ((#balance + #overdraw) >= 0);
                }
        }
}

```


# Compilation #
The first step is to produce the abstract Tamago-CDL file with the **tamagocdl** command:

```
tamagocdl -xml -o BankAccount.xml BankAccount.cdl
```

# Generation of the verification code #
The generation of the container is the role of contract compiler.

```
tamagocc BankAccount.xml
```