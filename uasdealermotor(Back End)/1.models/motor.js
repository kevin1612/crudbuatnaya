const mongoose = require('mongoose')
const Schema = mongoose.Schema

const motorSchema = new Schema({
  kodemotor : {
    type: String,
    indexes:{
      unique:true
    }
  },
  namamotor : {
    type: String
  },
  jenismotor : {
    type: String
  },
  warna:{
    type:String
  },
  stok : {
    type: String
  },
  harga :{
    type:String
  }
})


module.exports = mongoose.model('motor',motorSchema)
