const mongoose = require('mongoose')
const Schema = mongoose.Schema

const tranSchema = new Schema({
  nomorfaktur : {
    type: String,
    indexes:{
      unique:true
    }
  },
  kodemotor : {
    type: String
  },
  username : {
    type: String
  },
  tgltransaksi : {
    type: String
  },
  jumlahbeli :{
    type:String
  },
  harga:{
    type:String
  },
  bonus:{
    type:String
  }
})


module.exports = mongoose.model('transaksi',tranSchema)
