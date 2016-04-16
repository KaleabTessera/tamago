# Introduction #

The langage Tamago-CDL allows the description of the behavior with a finite state machine. This one governs the activation of some functionalities when the inner state (specific states of observers) is reach by the client).


# Structure #

A behavior is divided into three parts as follow:
```
behavior { 
   default state <initstate>;
   states { }
   transitions { }
}
```



  * The default state corresponds to the initial state in a finite state machine. Then, this one must be defined in the second part of the behavior.

  * The set of states is a suite of state declaration, where each state allows a set of functionalities (one per line). For example:

` state <name> { allow <func1>; allow <func2>; } `

This example declare a state `<name>` and in this state, the client can call the two functionalities `<func1>` and `<func2>`.

  * The last part is the set of transitions. A transition links a state to another through a functionality protected with a guard. For examples:

```
transition from <start> to <dest> with <func>;
transition from <start> to <dest> with <func> when <expression>;
```
  1. The first example describes a transition that starts from a state called `<start>` and the new state of the contract after calls the `<func>` functionality will be `<dest>`.
  1. The second example describes a guarded transition. We add a boolean expression to the transition that distinct the case where one functionality can give more that one effect in the contract.


# Example #

The `withdraw` functionality in the bank account service is available only when the balance is greater than zero otherwise an error will occur. This semantic is a specific case of the precondition and can be used to design the contract more easily.

We can resume the behavior of the bank account as follow:

```
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
```