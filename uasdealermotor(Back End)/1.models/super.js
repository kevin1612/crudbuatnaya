const mongoose = require('mongoose')
const Schema = mongoose.Schema

const adminSchema = new Schema({
  koderoot : {
    type: String,
    indexes:{
      unique:true
    }
  },
  password : {
    type: String
  }
})


module.exports = mongoose.model('super',adminSchema)
