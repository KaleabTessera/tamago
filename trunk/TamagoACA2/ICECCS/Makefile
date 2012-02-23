TAMAGOCDL=tamagocdl

SERVICES=cdls/ACASecurity.cdl cdls/Cheque.cdl cdls/Client.cdl cdls/DepositService.cdl cdls/Bank.cdl
COMPONENTS=cdls/CDeposit.cdl


XMLCDL=$(subst cdl,xml,$(SERVICES)) $(subst cdl,xml,$(COMPONENTS))

all: $(XMLCDL)
	@echo $^

xmls/%.xml: cdls/%.cdl
	tamagocdl $< > $@

code: $(XMLCDL)
	tamagocc --tamagoccpath xmls -d gen -noskeleton $^

testnominal:
	tamagotest --tamagoccpath xmls --debug 3 --logfile nominal_aca.log -d test --strategy nominal --junitsuffix Nominal --scenario-size 10 xmls/DepositSecurity.xml
testbad:
	tamagotest --tamagoccpath xmls --debug 3 --logfile badscenario_aca.log -d test --strategy badscenario --junitsuffix BadScenario --scenario-size 10 xmls/DepositSecurity.xml

testalltransitions:
	tamagotest --tamagoccpath xmls --debug 3 --logfile stdout -d test --strategy alltransitions --junitsuffix BadScenario --scenario-size 10 xmls/DepositSecurity.xml | tee alltransition_aca.log

# EXPERIMENTAL DONT USE IT 
skeleton: $(XMLCDL)
	tamagocc -tp xmls -d src xmls/CDeposit.xml

clean:
	echo === clean ===