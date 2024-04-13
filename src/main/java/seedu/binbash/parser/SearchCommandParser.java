package seedu.binbash.parser;

import seedu.binbash.command.SearchCommand;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.time.LocalDate;

/**
 * Parses command line arguments for creating a SearchCommand.
 */
public class SearchCommandParser extends DefaultParser {

    /**
     * Creates a new SearchCommandParser with the necessary options and option descriptions.
     */
    public SearchCommandParser() {
        options = new Options();
        new CommandOptionAdder(options)
            .addNameOption(false, "Search by name")
            .addDescriptionOption(false, "Search by description")
            .addQuantityOption(false, "Search by quantity")
            .addCostPriceOption(false, "Search by cost-price")
            .addSalePriceOption(false, "Search by sale-price")
            .addExpirationDateOption(false, "Search by expiry date")
            .addListOption(false, "Lists the first n results")
            .saveCommandOptionDescriptions("search");
    }

    /**
     * Parses the command line arguments to create a SearchCommand.
     *
     * @param commandArgs The command line arguments.
     * @return The parsed SearchCommand.
     * @throws ParseException If an error occurs during parsing.
     */
    public SearchCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = super.parse(options, commandArgs);

        boolean hasOption = false;
        SearchCommand searchCommand = new SearchCommand();

        if (commandLine.hasOption("name")) {
            String nameField = String.join(" ", commandLine.getOptionValues("name"));// Allow multiple arguments
            searchCommand.setNameField(nameField);
            hasOption = true;
        }

        if (commandLine.hasOption("description")) {
            String descriptionField = String.join(" ", commandLine.getOptionValues("description"));
            searchCommand.setDescriptionField(descriptionField);
            hasOption = true;
        }

        if (commandLine.hasOption("quantity")) {
            String[] rangeArgument = parseRangeArgument(commandLine.getOptionValue("quantity"), "quantity");
            int[] quantityRange = {Integer.MIN_VALUE, Integer.MAX_VALUE};
            if (rangeArgument[0] != "") {
                quantityRange[0] = Parser.parseIntOptionValue(rangeArgument[0], "quantity lower bound");
            }
            if (rangeArgument[1] != "") {
                quantityRange[1] = Parser.parseIntOptionValue(rangeArgument[1], "quantity upper bound");
            }
            searchCommand.setQuantityRange(quantityRange);
            hasOption = true;
        }

        if (commandLine.hasOption("cost-price")) {
            String[] rangeArgument = parseRangeArgument(commandLine.getOptionValue("cost-price"), "cost-price");
            double[] costPriceRange = {Double.MIN_VALUE, Double.MAX_VALUE};
            if (rangeArgument[0] != "") {
                costPriceRange[0] = Parser.parseDoubleOptionValue(rangeArgument[0], "cost price lower bound");
            }
            if (rangeArgument[1] != "") {
                costPriceRange[1] = Parser.parseDoubleOptionValue(rangeArgument[1], "cost price upper bound");
            }
            searchCommand.setCostPriceRange(costPriceRange);
            hasOption = true;
        }

        if (commandLine.hasOption("sale-price")) {
            String[] rangeArgument = parseRangeArgument(commandLine.getOptionValue("sale-price"), "sale-price");
            double[] salePriceRange = {Double.MIN_VALUE, Double.MAX_VALUE};
            if (rangeArgument[0] != "") {
                salePriceRange[0] = Parser.parseDoubleOptionValue(rangeArgument[0], "sale price lower bound");
            }
            if (rangeArgument[1] != "") {
                salePriceRange[1] = Parser.parseDoubleOptionValue(rangeArgument[1], "sale price upper bound");
            }
            searchCommand.setSalePriceRange(salePriceRange);
            hasOption = true;
        }

        if (commandLine.hasOption("expiry-date")) {
            String[] rangeArgument = parseRangeArgument(commandLine.getOptionValue("expiry-date"), "expiry-date");
            LocalDate[] expiryDateRange = {LocalDate.MIN, LocalDate.MAX};
            if (rangeArgument[0] != "") {
                expiryDateRange[0] = Parser.parseDateOptionValue(rangeArgument[0], "expiry date lower bound");
            }
            if (rangeArgument[1] != "") {
                expiryDateRange[1] = Parser.parseDateOptionValue(rangeArgument[1], "expiry date upper bound");
            }
            searchCommand.setExpiryDateRange(expiryDateRange);
            hasOption = true;
        }

        if (!hasOption) {
            throw new ParseException("At least one of -n, -d, -q, -c, -s, -e option required");
        }

        if (commandLine.hasOption("list")) {
            int numberOfResults = Parser.parseIntOptionValue(commandLine.getOptionValue("list"), "number of results");
            if (numberOfResults <= 0) {
                throw new ParseException("number of results must be positive");
            }
            searchCommand.setNumberOfResults(numberOfResults);
        }
        return searchCommand;
    }

    /**
     * Parses the range argument for quantity, cost price, sale price, or expiry date options.
     *
     * @param argument The range argument string.
     * @param option   The option for which the range argument is parsed.
     * @return An array containing the minimum and maximum values of the range.
     * @throws ParseException If the range argument is not in the correct format.
     */
    String[] parseRangeArgument(String argument, String option) throws ParseException {
        if (!argument.contains("..") || argument.length() < 3) {
            throw new ParseException("Format for " + option + " option: {min}..{max}. "
                    + "At least one of min or max is required.");
        }
        String[] argumentRange = {"", ""};
        String[] values = argument.split("\\Q..\\E");
        if (values[0].equals("")) {
            argumentRange[1] = values[1];
            return argumentRange;
        }
        argumentRange[0] = values[0];
        try {
            argumentRange[1] = values[1];
            return argumentRange;
        } catch (ArrayIndexOutOfBoundsException e) {
            return argumentRange;
        }
    }
}
