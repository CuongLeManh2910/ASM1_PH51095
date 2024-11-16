const express = require('express');

const router = express.Router();

module.exports = router;

router.get('/', (req,res) => {
    res.send('vao api mobile');
})

const mongoose = require('mongoose');

const phoneModel = require('./phoneModel');

const COMMON = require('./COMMON');

router.get('/list', async (req,res) => {
    await mongoose.connect(COMMON.uri)

    let phones = await phoneModel.find();

    console.log(phones);

    res.send(phones);
})

const bodyParser = require("body-parser");
router.use(bodyParser.json());
router.use(bodyParser.urlencoded({ extended: true }));

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

// router.delete('/xoa_dt/:id', async (req, res) => { // router.delete
//     await mongoose.connect(COMMON.uri);

//     let id = req.params.id;
//     console.log(id);

//     // xu ly loi khi id khong dung
//     await phoneModel.deleteOne({_id: id});

//     let phones = await phoneModel.find(); // list json array

//     res.send(phones);
// })

// router.get('/xoa/:id', async (req, res) => {
//     await mongoose.connect(COMMON.uri);

//     let id = req.params.id;

//     console.log(id);

//     // xu li loi khi id ko dung
//     await phoneModel.deleteOne({_id:id});

//     res.redirect('../list')

// })

router.delete('/xoa_dt/:id', async (req, res) => { // router.delete
    await mongoose.connect(COMMON.uri);

    let id = req.params.id;
    console.log(id);

    // xu ly loi khi id khong dung
    await phoneModel.deleteOne({_id: id});

    let phones = await phoneModel.find(); // list json array

    res.send(phones);
}) 

router.get('/xoa/:id', async (req, res) => { // router.delete
    await mongoose.connect(COMMON.uri);

    let id = req.params.id;
    console.log(id);

    // xu ly loi khi id khong dung
    await phoneModel.deleteOne({_id: id});

    res.redirect('../list')
}) 

router.get('/update/:ten', async (req, res)=> {
    await mongoose.connect(COMMON.uri);

    console.log('ket noi db thanh cong');

    let phoneName = req.params.ten;
    console.log(phoneName);

    let newPhoneName = phoneName + 'ban moi 2024 ';

    await phoneModel.updateOne({ten: phoneName}, {ten: newPhoneName});

    let dienthoais = await phoneModel.find({});

    res.send(dienthoais);
})