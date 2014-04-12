package com.puresoltechnologies.purifinity.framework.commons.utils.business;

import java.util.Locale;

/**
 * This enum contains the business duties defined in InCoTerms. See
 * {@link InternationalCommercialTerm} for more information on this topic.
 * 
 * The duties are used to specify the different term in relationship to
 * responsibilities.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum BusinessDuty {

	LOADING_ON_TRUCK, EXPORT_CUSTOMS_DECLARATION, CARRIAGE_TO_PORT_OF_EXPORT, UNLOADING_OF_TRUCK_IN_PORT_OF_EXPORT, LOADING_CHARGES_IN_PORT_OF_EXPORT, CARRIAGE_TO_PORT_OF_IMPORT, UNLOADING_CHARGES_IN_PORT_OF_IMPORT, LOADING_ON_TRUCK_IN_PORT_OF_IMPORT, CARRIAGE_TO_PLACE_OF_DESTINATION, INSURANCE, IMPORT_CUSTOMS_CLEARANCE, IMPORT_TAXES;

	public String getName(Locale locale) {
		switch (this) {
		case LOADING_ON_TRUCK:
			return "Loading on truck (carrier)";
		case EXPORT_CUSTOMS_DECLARATION:
			return "Export-customs declaration";
		case CARRIAGE_TO_PORT_OF_EXPORT:
			return "Carriage to port of export";
		case UNLOADING_OF_TRUCK_IN_PORT_OF_EXPORT:
			return "Unloading of truck in port of export";
		case LOADING_CHARGES_IN_PORT_OF_EXPORT:
			return "Loading charges in port of export";
		case CARRIAGE_TO_PORT_OF_IMPORT:
			return "Carriage to port of import";
		case UNLOADING_CHARGES_IN_PORT_OF_IMPORT:
			return "Unloading charges in port of import";
		case LOADING_ON_TRUCK_IN_PORT_OF_IMPORT:
			return "Loading on truck in port of import";
		case CARRIAGE_TO_PLACE_OF_DESTINATION:
			return "Carriage to place of destination";
		case INSURANCE:
			return "Insurance";
		case IMPORT_CUSTOMS_CLEARANCE:
			return "Import customs clearance";
		case IMPORT_TAXES:
			return "Import taxes";
		default:
			throw new RuntimeException("Unknown business duty '" + name()
					+ "'!");
		}
	}
}
