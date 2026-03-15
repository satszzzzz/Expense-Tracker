from dotenv import load_dotenv
from groq import Groq
import os
import instructor
from .models import Expense

class LlmService():
    def __init__(self):
        load_dotenv()
        self.prompt = """
            You are a bank SMS parser. Extract only these 3 fields from the SMS:
                - amount: the transaction amount as a string (e.g. "5000.00"). Null if not found.
                - merchant: the merchant or place name (e.g. "Swiggy", "Amazon"). Null if not found.
                - currency: the currency code (e.g. "INR", "USD"). Default to "INR" for Indian bank SMS.
                - Get the outputs in the model specified which is amount, merchant and currency as json and with their values after extraction
                
                Return nothing else. No explanations
        """
        self.apiKey = os.getenv("GROQ_API_KEY")
    
    def runLLM(self, message, sender):
        """
        Calls Groq LLM via instructor where instructor forces the LLM to return
        a Expense pydantic object
        """

        user_message = f"SMS Sender: {sender or 'Unknown'}\nSMS Text: {message}"

        raw_client = Groq(api_key = self.apiKey)
        client = instructor.from_groq(raw_client, mode=instructor.Mode.JSON)

        output = client.chat.completions.create(
            model = "openai/gpt-oss-120b",
            max_tokens = 1000,
            messages = [
                {"role": "system", "content": self.prompt},
                {"role": "user", "content": user_message}
            ],
            response_model = Expense
        )
        return output
