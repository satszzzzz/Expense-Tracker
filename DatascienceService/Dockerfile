FROM python:3.11-alpine

WORKDIR /app

COPY requirements.txt .

COPY service ./service
COPY utils ./utils
COPY main.py .

RUN apk add --no-cache gcc musl-dev libffi-dev
RUN pip install --no-cache-dir -r requirements.txt

CMD ["python", "main.py"]

EXPOSE 8010