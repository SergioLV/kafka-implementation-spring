import requests
from faker import Faker
from random import randint
from datetime import datetime
import time
import logging
import random

logging.basicConfig(format='%(asctime)s %(message)s', encoding='utf-8', level=logging.INFO)
logging.info("============================")
logging.info("SCRIPT FOR MOCKING NEW SALES")
logging.info("============================")

CARTS = {
    # Udp
    "0": {
        "latitude":"-33.45254132104446",
        "longitude":"-70.66057572725346",
        "stock": 20
    },
    # Parque O'Higgins
    "1": {
        "latitude":"-33.46043177647501",
        "longitude":"-70.6570643223665",
        "stock": 20
    },
    #  Parque Inés de Suárez
    "2": {
        "latitude":"-33.44177731585045",
        "longitude":"-70.61208052301797",
        "stock": 20
    },
}

NEW_SALES_URL = 'http://192.168.1.111:8081/api/v1/add/sale'

fake = Faker()

for i in range(1_000_000):
    cart_id = randint(0,2)
    if(CARTS[str(cart_id)]["stock"] > 0):
        data={}
        data['cartId']=cart_id
        data['clientName'] = fake.name()
        data['amount'] = randint(1,10)
        data['date'] = datetime.now().strftime("%d/%m/%Y %H:%M:%S")
        CARTS[str(cart_id)]["stock"] -= 1
        data['remainingStock'] = CARTS[str(cart_id)]["stock"]
        data['latitude'] = CARTS[str(cart_id)]["latitude"]
        data['longitude'] = CARTS[str(cart_id)]["longitude"]
        data['saleType'] = random.choice(["credit", "debit"])
        logging.info(f'Cart Id: %s, Amount: %s, SaleType: %s, RemainingStock: %s' % (data['cartId'], data['amount'], data['saleType'], data['remainingStock']))

        time.sleep(1)
        res = requests.post(NEW_SALES_URL, json=data)
    else:
        continue
    #  cd .\receiver\resources\scripts\sales\