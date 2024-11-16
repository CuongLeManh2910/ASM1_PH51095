const express = require('express');

const app = express();

const port = 3000;

app.listen(port, () => {
    console.log(`Server is running on http:${port}`);
});

const COMMON = require('./COMMON')

const uri = COMMON.uri;

const mongoose = require('mongoose');

const phoneModel = require('./phoneModel');

const apiMobile = require('./api');
const router = require('./api');
app.use('/api', apiMobile);

router.get('/',  async (req,res) => {
    await mongoose.connect(COMMON.uri)

    let phones = await phoneModel.find();

    console.log(phones);

    res.send(phones);
})


router.post('/add_phone', async (req, res) =>{
    await mongoose.connect(COMMON.uri);

    // let phone = {
    //     ten: 'Nokia 1280',
    //     hang: 'Nokia',
    //     namSX: 2012,
    //     gia: 500,
    // }

    let phone = req.body;

    console.log(phone)

    let kq = await phoneModel.create(phone);
    console.log(kq);

    let phones = await phoneModel.find();

    res.send(phones);
})

app.get('/xoa/:id', async (req, res) => {
    await mongoose.connect(uri);

    let id = req.params.id;

    console.log(id);

    // xu li loi khi id ko dung
    await phoneModel.deleteOne({_id:id});

    res.redirect('../list')

})

app.get('/update/:ten', async (req, res)=> {
    await mongoose.connect(uri);

    console.log('ket noi db thanh cong');

    let phoneName = req.params.ten;
    console.log(phoneName);

    let newPhoneName = phoneName + 'ban moi 2024 ';

    await phoneModel.updateOne({ten: phoneName}, {ten: newPhoneName});

    let dienthoais = await phoneModel.find({});

    res.send(dienthoais);
})