package seedu.binbash.parser;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

public class CommandOptionAdder {
    Options options;

    public CommandOptionAdder(Options options) {
        this.options = options;
    }

    private Option getRetailItemOption() {
        Option reItemOption = Option.builder("re")
                .hasArg(false)
                .required(true)
                .longOpt("retail")
                .desc("Add a Retail Item.")
                .argName("retail")
                .build();

        return reItemOption;
    }

    private Option getOperationalItemOption() {
        Option opItemOption = Option.builder("op")
                .hasArg(false)
                .required(true)
                .longOpt("operational")
                .desc("Add an Operational Item.")
                .argName("operational")
                .build();

        return opItemOption;
    }

    private Option getItemNameOption() {
        Option opItemOption = Option.builder("n")
                .hasArg(true)
                .required(true)
                .longOpt("name")
                .desc("Update using name")
                .argName("name")
                .build();

        return opItemOption;
    }

    private Option getItemIndexOption() {
        Option opItemOption = Option.builder("i")
                .hasArg(true)
                .required(true)
                .longOpt("index")
                .desc("Update using index")
                .argName("index")
                .build();

        return opItemOption;
    }

    CommandOptionAdder addItemTypeOptionGroup() {
        OptionGroup itemTypeOptionGroup = new OptionGroup()
                .addOption(getRetailItemOption())
                .addOption(getOperationalItemOption());

        itemTypeOptionGroup.setRequired(true);
        options.addOptionGroup(itemTypeOptionGroup);
        return this;
    }

    CommandOptionAdder addItemNameAndIndexOptionGroup() {
        OptionGroup itemNameAndInxdexOptionGroup = new OptionGroup()
                .addOption(getItemIndexOption())
                .addOption(getItemNameOption());

        itemNameAndInxdexOptionGroup.setRequired(true);
        options.addOptionGroup(itemNameAndInxdexOptionGroup);
        return this;
    }

    CommandOptionAdder addNameOption(boolean isRequired, String description) {
        Option nameOption = Option.builder("n")
                .hasArgs() // potentially more than 1 input
                .required(isRequired)
                .longOpt("name")
                .desc(description)
                .build();
        options.addOption(nameOption);
        return this;
    }

    CommandOptionAdder addIndexOption(boolean isRequired, String description) {
        Option nameOption = Option.builder("i")
                .hasArg(true)
                .required(isRequired)
                .longOpt("index")
                .desc(description)
                .argName("index")
                .build();
        options.addOption(nameOption);
        return this;
    }

    CommandOptionAdder addDescriptionOption(boolean isRequired, String description) {
        Option descOption = Option.builder("d")
                .hasArgs()
                .required(isRequired)
                .longOpt("description")
                .desc(description)
                .build();
        options.addOption(descOption);
        return this;
    }

    CommandOptionAdder addCostPriceOption(boolean isRequired, String description) {
        Option costOption = Option.builder("c")
                .hasArg(true)
                .required(isRequired)
                .numberOfArgs(1)
                .longOpt("cost-price")
                .desc(description)
                .build();
        options.addOption(costOption);
        return this;
    }

    CommandOptionAdder addQuantityOption(boolean isRequired, String description) {
        Option quantOption = Option.builder("q")
                .hasArg(true)
                .required(isRequired)
                .numberOfArgs(1)
                .longOpt("quantity")
                .desc(description)
                .build();
        options.addOption(quantOption);
        return this;
    }

    CommandOptionAdder addSalePriceOption(boolean isRequired, String description) {
        Option saleOption = Option.builder("s")
                .hasArg(true)
                .required(isRequired)
                .numberOfArgs(1)
                .longOpt("sale-price")
                .desc(description)
                .build();
        options.addOption(saleOption);
        return this;
    }

    CommandOptionAdder addExpirationDateOption(boolean isRequired, String description) {
        Option expiryOption = Option.builder("e")
                .hasArg(true)
                .required(isRequired)
                .numberOfArgs(1)
                .longOpt("expiry-date")
                .desc(description)
                .build();
        options = options.addOption(expiryOption);
        return this;
    }

    CommandOptionAdder addThresholdOption(boolean isRequired, String description) {
        Option thresholdOption = Option.builder("t")
                .hasArg(true)
                .required(isRequired)
                .longOpt("threshold")
                .desc(description)
                .argName("threshold")
                .build();

        options = options.addOption(thresholdOption);
        return this;
    }

    CommandOptionAdder addListOption(boolean isRequired, String description) {
        Option nameOption = Option.builder("l")
                .hasArg()
                .argName("n")
                .required(isRequired)
                .longOpt("list")
                .desc(description)
                .build();
        options.addOption(nameOption);
        return this;
    }
}
