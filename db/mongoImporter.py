# Simple python script for
#     - connecting with mongo database
#     - insert initial example products
#     - upload example photos to gridFS database
#     USE EVERYTIME WHEN CREATE NEW MONGO INSTANCE, OTHERWISE DB WILL BE EMPTY :)

import pymongo
import gridfs
import json

client = pymongo.MongoClient('mongodb://admin:admin@localhost:27017/?authMechanism=DEFAULT')
db = client.get_database('app_shop')
products = db.get_collection('products')
fs = gridfs.GridFS(db)

with open('data.json', encoding='utf-8') as f:
    data = json.load(f, )
    for product in data:
        path = '../' + product['imgPath']
        del product['imgPath']
        id = products.insert_one(product).inserted_id
        with open(path, 'rb') as image:
            fs.put(image, product_id=id, file_type='png')
