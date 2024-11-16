const mongoose = require('mongoose');

const phoneSchema = new mongoose.Schema({
    ten: {
        type: String,
        require: true,
    },
    hang: {
        type: String,
    },
    namSX: {
        type: Number,
    },
    gia: {
        type: Number,
    },

});

const phoneModel = new mongoose.model('phone', phoneSchema);
module.exports = phoneModel;