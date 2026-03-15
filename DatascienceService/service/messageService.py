from utils.messageUtil import MesssageUtil
from service.llmService import LlmService

class MessageService():
    def __init__(self):
        self.messageUtil = MesssageUtil()
        self.llmService = LlmService()
    
    def process_message(self, message, sender):
        if self.messageUtil.isBankSms(message):
            return self.llmService.runLLM(message, sender)
        else:
            return None