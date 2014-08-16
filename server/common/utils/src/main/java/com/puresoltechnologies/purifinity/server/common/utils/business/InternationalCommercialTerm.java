package com.puresoltechnologies.purifinity.server.common.utils.business;

import java.util.Locale;

/**
 * This enum contains the International Commercial Terms. These terms are
 * defined by the International Chamber of Commerce. For more information have a
 * look to: http://en.wikipedia.org/wiki/Incoterms
 * 
 * @author Rick-Rainer Ludwig
 */
public enum InternationalCommercialTerm {

	EXW, FCA, FAS, FOB, CFR, CIF, DAT, DAP, CPT, CIP, DDP;

	public static String getInCoTermVersion() {
		return "2010";
	}

	/**
	 * This method returns the short name of the term. This is the abbreviation.
	 * 
	 * @return
	 */
	public String getDisplayName() {
		return name();
	}

	/**
	 * This method returns the term's long name. It can be put next to the
	 * abbreviation for example.
	 * 
	 * @param locale
	 * @return
	 */
	public String getDisplayLongName(Locale locale) {
		switch (this) {
		case EXW:
			return "Ex Works (named place of delivery)";
		case FCA:
			return "Free Carrier (named place of delivery)";
		case FAS:
			return "Free Alongside Ship (named port of shipment)";
		case FOB:
			return "Free on Board (named port of shipment)";
		case CFR:
			return "Cost and Freight (named port of destination)";
		case CIF:
			return "Cost, Insurance and Freight (named port of destination)";
		case CPT:
			return "Carriage Paid To (named place of destination)";
		case CIP:
			return "Carriage and Insurance Paid to (named place of destination)";
		case DAT:
			return "Delivered at Terminal (named terminal at port or place of destination)";
		case DAP:
			return "Delivered at Place (named place of destination)";
		case DDP:
			return "Delivered Duty Paid (named place of destination)";
		default:
			throw new RuntimeException("Unknown InCoTerm '" + name() + "'!");
		}
	}

	/**
	 * This method returns the description for the term. This description can be
	 * shown as tool tip for instance.
	 * 
	 * @param locale
	 * @return
	 */
	public String getDescription(Locale locale) {
		switch (this) {
		case EXW:
			return "The seller makes the goods available at its premises. This term places the maximum obligation on the buyer and minimum obligations on the seller. The Ex Works term is often used when making an initial quotation for the sale of goods without any costs included. EXW means that a seller has the goods ready for collection at his premises (works, factory, warehouse, plant) on the date agreed upon. The buyer pays all transportation costs and also bears the risks for bringing the goods to their final destination. The seller doesn't load the goods on collecting vehicles and doesn't clear them for export. If the seller does load the good, he does so at buyer's risk and cost. If parties wish seller to be responsible for the loading of the goods on departure and to bear the risk and all costs of such loading, this must be made clear by adding explicit wording to this effect in the contract of sale.";
		case FCA:
			return "The seller hands over the goods, cleared for export, into the disposal of the first carrier (named by the buyer) at the named place. The seller pays for carriage to the named point of delivery, and risk passes when the goods are handed over to the first carrier.";
		case CPT:
			return "The seller pays for carriage. Risk transfers to buyer upon handing goods over to the first carrier.";
		case CIP:
			return "The containerized transport/multimodal equivalent of CIF. Seller pays for carriage and insurance to the named destination point, but risk passes when the goods are handed over to the first carrier.";
		case FAS:
			return "The seller must place the goods alongside the ship at the named port. The seller must clear the goods for export. Suitable only for maritime transport but NOT for multimodal sea transport in containers (see Incoterms 2010, ICC publication 715). This term is typically used for heavy-lift or bulk cargo.";
		case FOB:
			return "The seller must load the goods on board the vessel nominated by the buyer. Cost and risk are divided when the goods are actually on board of the vessel (this rule is new!). The seller must clear the goods for export. The term is applicable for maritime and inland waterway transport only but NOT for multimodal sea transport in containers (see Incoterms 2010, ICC publication 715). The buyer must instruct the seller the details of the vessel and the port where the goods are to be loaded, and there is no reference to, or provision for, the use of a carrier or forwarder. This term has been greatly misused over the last three decades ever since Incoterms 1980 explained that FCA should be used for container shipments.";
		case CFR:
			return "Seller must pay the costs and freight to bring the goods to the port of destination. However, risk is transferred to the buyer once the goods are loaded on the vessel (this rule is new!). Maritime transport only and Insurance for the goods is NOT included. This term is formerly known as CNF (C&F).";
		case CIF:
			return "Exactly the same as CFR except that the seller must in addition procure and pay for the insurance. Maritime transport only.";
		case DAT:
			return "Seller pays for carriage to the terminal, except for costs related to import clearance, and assumes all risks up to the point that the goods are unloaded at the terminal.";
		case DAP:
			return "Seller pays for carriage to the named place, except for costs related to import clearance, and assumes all risks prior to the point that the goods are ready for unloading by the buyer.";
		case DDP:
			return "Seller is responsible for delivering the goods to the named place in the country of the buyer, and pays all costs in bringing the goods to the destination including import duties and taxes. This term places the maximum obligations on the seller and minimum obligations on the buyer.";
		default:
			throw new RuntimeException("Unknown InCoTerm '" + name() + "'!");
		}
	}

	public BusinessParty getResponsibleParty(BusinessDuty duty) {
		switch (this) {
		case EXW:
			return getResponsiblePartyForEXW(duty);
		case FCA:
			return getResponsiblePartyForFCA(duty);
		case FAS:
			return getResponsiblePartyForFAS(duty);
		case FOB:
			return getResponsiblePartyForFOB(duty);
		case CFR:
			return getResponsiblePartyForCFR(duty);
		case CIF:
			return getResponsiblePartyForCIF(duty);
		case DAT:
			return getResponsiblePartyForDAT(duty);
		case DAP:
			return getResponsiblePartyForDAP(duty);
		case CPT:
			return getResponsiblePartyForCPT(duty);
		case CIP:
			return getResponsiblePartyForCIP(duty);
		case DDP:
			return getResponsiblePartyForDDP(duty);
		default:
			throw new RuntimeException("Unknown InCoTerm '" + name() + "'!");
		}
	}

	private BusinessParty getResponsiblePartyForEXW(BusinessDuty duty) {
		switch (duty) {
		case LOADING_ON_TRUCK:
			return BusinessParty.BUYER;
		case EXPORT_CUSTOMS_DECLARATION:
			return BusinessParty.BUYER;
		case CARRIAGE_TO_PORT_OF_EXPORT:
			return BusinessParty.BUYER;
		case UNLOADING_OF_TRUCK_IN_PORT_OF_EXPORT:
			return BusinessParty.BUYER;
		case LOADING_CHARGES_IN_PORT_OF_EXPORT:
			return BusinessParty.BUYER;
		case CARRIAGE_TO_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case UNLOADING_CHARGES_IN_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case LOADING_ON_TRUCK_IN_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case CARRIAGE_TO_PLACE_OF_DESTINATION:
			return BusinessParty.BUYER;
		case INSURANCE:
			return BusinessParty.BUYER;
		case IMPORT_CUSTOMS_CLEARANCE:
			return BusinessParty.BUYER;
		case IMPORT_TAXES:
			return BusinessParty.BUYER;
		default:
			throw new RuntimeException("Unknown business duty '" + name()
					+ "'!");
		}
	}

	private BusinessParty getResponsiblePartyForFCA(BusinessDuty duty) {
		switch (duty) {
		case LOADING_ON_TRUCK:
			return BusinessParty.SELLER;
		case EXPORT_CUSTOMS_DECLARATION:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case UNLOADING_OF_TRUCK_IN_PORT_OF_EXPORT:
			return BusinessParty.BUYER;
		case LOADING_CHARGES_IN_PORT_OF_EXPORT:
			return BusinessParty.BUYER;
		case CARRIAGE_TO_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case UNLOADING_CHARGES_IN_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case LOADING_ON_TRUCK_IN_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case CARRIAGE_TO_PLACE_OF_DESTINATION:
			return BusinessParty.BUYER;
		case INSURANCE:
			return BusinessParty.BUYER;
		case IMPORT_CUSTOMS_CLEARANCE:
			return BusinessParty.BUYER;
		case IMPORT_TAXES:
			return BusinessParty.BUYER;
		default:
			throw new RuntimeException("Unknown business duty '" + name()
					+ "'!");
		}
	}

	private BusinessParty getResponsiblePartyForFAS(BusinessDuty duty) {
		switch (duty) {
		case LOADING_ON_TRUCK:
			return BusinessParty.SELLER;
		case EXPORT_CUSTOMS_DECLARATION:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case UNLOADING_OF_TRUCK_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case LOADING_CHARGES_IN_PORT_OF_EXPORT:
			return BusinessParty.BUYER;
		case CARRIAGE_TO_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case UNLOADING_CHARGES_IN_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case LOADING_ON_TRUCK_IN_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case CARRIAGE_TO_PLACE_OF_DESTINATION:
			return BusinessParty.BUYER;
		case INSURANCE:
			return BusinessParty.BUYER;
		case IMPORT_CUSTOMS_CLEARANCE:
			return BusinessParty.BUYER;
		case IMPORT_TAXES:
			return BusinessParty.BUYER;
		default:
			throw new RuntimeException("Unknown business duty '" + name()
					+ "'!");
		}
	}

	private BusinessParty getResponsiblePartyForFOB(BusinessDuty duty) {
		switch (duty) {
		case LOADING_ON_TRUCK:
			return BusinessParty.SELLER;
		case EXPORT_CUSTOMS_DECLARATION:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case UNLOADING_OF_TRUCK_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case LOADING_CHARGES_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case UNLOADING_CHARGES_IN_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case LOADING_ON_TRUCK_IN_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case CARRIAGE_TO_PLACE_OF_DESTINATION:
			return BusinessParty.BUYER;
		case INSURANCE:
			return BusinessParty.BUYER;
		case IMPORT_CUSTOMS_CLEARANCE:
			return BusinessParty.BUYER;
		case IMPORT_TAXES:
			return BusinessParty.BUYER;
		default:
			throw new RuntimeException("Unknown business duty '" + name()
					+ "'!");
		}
	}

	private BusinessParty getResponsiblePartyForCFR(BusinessDuty duty) {
		switch (duty) {
		case LOADING_ON_TRUCK:
			return BusinessParty.SELLER;
		case EXPORT_CUSTOMS_DECLARATION:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case UNLOADING_OF_TRUCK_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case LOADING_CHARGES_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case UNLOADING_CHARGES_IN_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case LOADING_ON_TRUCK_IN_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case CARRIAGE_TO_PLACE_OF_DESTINATION:
			return BusinessParty.BUYER;
		case INSURANCE:
			return BusinessParty.BUYER;
		case IMPORT_CUSTOMS_CLEARANCE:
			return BusinessParty.BUYER;
		case IMPORT_TAXES:
			return BusinessParty.BUYER;
		default:
			throw new RuntimeException("Unknown business duty '" + name()
					+ "'!");
		}
	}

	private BusinessParty getResponsiblePartyForCIF(BusinessDuty duty) {
		switch (duty) {
		case LOADING_ON_TRUCK:
			return BusinessParty.SELLER;
		case EXPORT_CUSTOMS_DECLARATION:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case UNLOADING_OF_TRUCK_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case LOADING_CHARGES_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case UNLOADING_CHARGES_IN_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case LOADING_ON_TRUCK_IN_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case CARRIAGE_TO_PLACE_OF_DESTINATION:
			return BusinessParty.BUYER;
		case INSURANCE:
			return BusinessParty.SELLER;
		case IMPORT_CUSTOMS_CLEARANCE:
			return BusinessParty.BUYER;
		case IMPORT_TAXES:
			return BusinessParty.BUYER;
		default:
			throw new RuntimeException("Unknown business duty '" + name()
					+ "'!");
		}
	}

	private BusinessParty getResponsiblePartyForDAT(BusinessDuty duty) {
		switch (duty) {
		case LOADING_ON_TRUCK:
			return BusinessParty.SELLER;
		case EXPORT_CUSTOMS_DECLARATION:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case UNLOADING_OF_TRUCK_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case LOADING_CHARGES_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case UNLOADING_CHARGES_IN_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case LOADING_ON_TRUCK_IN_PORT_OF_IMPORT:
			return BusinessParty.BUYER;
		case CARRIAGE_TO_PLACE_OF_DESTINATION:
			return BusinessParty.BUYER;
		case INSURANCE:
			return BusinessParty.BUYER;
		case IMPORT_CUSTOMS_CLEARANCE:
			return BusinessParty.BUYER;
		case IMPORT_TAXES:
			return BusinessParty.BUYER;
		default:
			throw new RuntimeException("Unknown business duty '" + name()
					+ "'!");
		}
	}

	private BusinessParty getResponsiblePartyForDAP(BusinessDuty duty) {
		switch (duty) {
		case LOADING_ON_TRUCK:
			return BusinessParty.SELLER;
		case EXPORT_CUSTOMS_DECLARATION:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case UNLOADING_OF_TRUCK_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case LOADING_CHARGES_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case UNLOADING_CHARGES_IN_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case LOADING_ON_TRUCK_IN_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PLACE_OF_DESTINATION:
			return BusinessParty.SELLER;
		case INSURANCE:
			return BusinessParty.BUYER;
		case IMPORT_CUSTOMS_CLEARANCE:
			return BusinessParty.BUYER;
		case IMPORT_TAXES:
			return BusinessParty.BUYER;
		default:
			throw new RuntimeException("Unknown business duty '" + name()
					+ "'!");
		}
	}

	private BusinessParty getResponsiblePartyForCPT(BusinessDuty duty) {
		switch (duty) {
		case LOADING_ON_TRUCK:
			return BusinessParty.SELLER;
		case EXPORT_CUSTOMS_DECLARATION:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case UNLOADING_OF_TRUCK_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case LOADING_CHARGES_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case UNLOADING_CHARGES_IN_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case LOADING_ON_TRUCK_IN_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PLACE_OF_DESTINATION:
			return BusinessParty.SELLER;
		case INSURANCE:
			return BusinessParty.BUYER;
		case IMPORT_CUSTOMS_CLEARANCE:
			return BusinessParty.BUYER;
		case IMPORT_TAXES:
			return BusinessParty.BUYER;
		default:
			throw new RuntimeException("Unknown business duty '" + name()
					+ "'!");
		}
	}

	private BusinessParty getResponsiblePartyForCIP(BusinessDuty duty) {
		switch (duty) {
		case LOADING_ON_TRUCK:
			return BusinessParty.SELLER;
		case EXPORT_CUSTOMS_DECLARATION:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case UNLOADING_OF_TRUCK_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case LOADING_CHARGES_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case UNLOADING_CHARGES_IN_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case LOADING_ON_TRUCK_IN_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PLACE_OF_DESTINATION:
			return BusinessParty.SELLER;
		case INSURANCE:
			return BusinessParty.SELLER;
		case IMPORT_CUSTOMS_CLEARANCE:
			return BusinessParty.BUYER;
		case IMPORT_TAXES:
			return BusinessParty.BUYER;
		default:
			throw new RuntimeException("Unknown business duty '" + name()
					+ "'!");
		}
	}

	private BusinessParty getResponsiblePartyForDDP(BusinessDuty duty) {
		switch (duty) {
		case LOADING_ON_TRUCK:
			return BusinessParty.SELLER;
		case EXPORT_CUSTOMS_DECLARATION:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case UNLOADING_OF_TRUCK_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case LOADING_CHARGES_IN_PORT_OF_EXPORT:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case UNLOADING_CHARGES_IN_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case LOADING_ON_TRUCK_IN_PORT_OF_IMPORT:
			return BusinessParty.SELLER;
		case CARRIAGE_TO_PLACE_OF_DESTINATION:
			return BusinessParty.SELLER;
		case INSURANCE:
			return BusinessParty.BUYER;
		case IMPORT_CUSTOMS_CLEARANCE:
			return BusinessParty.SELLER;
		case IMPORT_TAXES:
			return BusinessParty.SELLER;
		default:
			throw new RuntimeException("Unknown business duty '" + name()
					+ "'!");
		}
	}

}
