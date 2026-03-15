from pydantic import BaseModel, Field
from typing import Optional

class Expense(BaseModel):
    amount: Optional[str] = Field(title="expense", description="Amount credited/debited in the transaction")
    merchant: Optional[str] = Field(title="merchant", description="Merchant name or transaction description")
    currency: Optional[str] = Field(title="currency", description="Currency in which the transaction is carried out")

class MessageRequest(BaseModel):
    message: str
    sender: str = None 