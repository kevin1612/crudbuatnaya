const mongoose = require('mongoose')
const Schema = mongoose.Schema

const pesanSchema = new Schema({

  kodemotor : {
    type: String
  },
  username : {
    type: String
  },
  jumlahbeli :{
    type:String
  }
})


module.exports = mongoose.model('pemesanan',pesanSchema)
