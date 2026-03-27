from fastapi import FastAPI
import uvicorn
from service.messageService import MessageService
from service.models import Expense, MessageRequest
from kafka import KafkaProducer
import json

app = FastAPI()

producer = KafkaProducer(
    bootstrap_servers = "kafka:9092",
    value_serializer = lambda v: json.dumps(v).encode('utf-8')
)

@app.post("/ds/v1/message", response_model=Expense)
async def handle_message(request: MessageRequest):
    resultant_output = MessageService().process_message(request.message, request.sender)
    print(resultant_output.model_dump())
    producer.send("expense-topic", value=resultant_output.model_dump())
    return resultant_output

if __name__ == "__main__":
    uvicorn.run("main:app", host="0.0.0.0", port=8010, reload=True)