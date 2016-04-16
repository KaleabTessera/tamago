# Prerequisite #

The contract of the component and all provided services must be generated before try the creation of a Tamago component.

# Creation #

All manipulations of the component must be done through the `tamago.ext.tamagocc.TamagoCC` static class. This class is include in the basic framework tamago.jar


This class contains several functions:
  * `LookUp`: search and creat a component with his full name
  * `Bind`: bind to a component a required service through its label.
  * `getTamagoCCType`: give a special class corresponding to the reification of the component contract informations.
  * `HotSwapping`: change on the fly the component business code. (see the `core_set_properties`)


# Example #

## Basic example ##

```
try {
   BankAccount p = (BankAccount) TamagoCC.LookUp("tamago.bankaccount.BankAccountComponent");
   // use p normally
} catch(TamagoException te) {
   // manage exception
}
```

With this example, we see how initialize basically a component of the BankAccount service.

The produced component is wrapped in a container. The latter checks the contracts transparently for the client.

**Remark**: with this example, we assume the business code is the generated skeleton `BankAccountComponentStub` or `BankAccountComponentBusiness`. For more complex creation see next sections.

## Example with external business code ##

Sometimes you want to use your own component filename (whereas use the convention applied in Tamago). Consider the BankAccount example, if the component business code is the class _MyBankAccount_ then the creation of the component becomes:

```
try {
   MyBankAccount mba; // initialize it yourself as needed
   BankAccount p = (BankAccount) TamagoCC.LookUp("tamago.bankaccount.BankAccountComponent",mba);
   // use p normally
} catch(TamagoException te) {
   // manage exception
}
```

In this case, Tamago gives your own component `mba` to a wrapper that checks the contract on it. Obviously the _MyBankAccount_ must herit of the component interface.


# Remark #

The philosophy of Tamago component consist to manipulate a component as a reference on a Service.