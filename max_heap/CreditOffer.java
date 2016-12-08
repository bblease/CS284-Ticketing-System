/**
*Creates Credit Offer Object
*@author Ben Blease
*@since 11/19/15
*I pledge my honor that I have abided by the Stevens honor system.
**/

public class CreditOffer
	{
		public double membershipFee;
		public double cbr;
		public double apr;

		/**
		*Creates a node storing input data
		*@param membershipFee: expense of membership
		*@param CBR: Cash back rate
		*@param APR: Annual percentage rate
		**/
		public CreditOffer(double membershipFee, double cbr, double apr)
		{
			this.membershipFee = membershipFee;
			this.cbr = cbr;
			this.apr = apr;
		}
	}