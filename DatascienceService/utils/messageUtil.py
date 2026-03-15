import re

class MesssageUtil:
    def isBankSms(self, message: str) -> bool:
        words_to_search = [
            "debited", "credited", "transaction", "balance", "a/c", "acct", 
            "withdrawn", "deposit", "transfer", "upi", "neft", "imps", "atm",
            "spent", "payment", "purchase", "rs.", "inr", "₹", "bank"
        ]
        return any(keyword in message for keyword in words_to_search)