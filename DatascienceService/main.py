from fastapi import FastAPI
import uvicorn
from service.messageService import MessageService
from service.models import Expense, MessageRequest

app = FastAPI()

@app.post("/ds/v1/message", response_model=Expense)
async def handle_message(request: MessageRequest):
    resultant_output = MessageService().process_message(request.message, request.sender)
    return resultant_output

if __name__ == "__main__":
    uvicorn.run("main:app", host="0.0.0.0", port=8000, reload=True)