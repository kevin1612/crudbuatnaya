const mongoose = require('mongoose')
const Schema = mongoose.Schema

const customerSchema = new Schema({
  username : {
    type: String,
    indexes:{
      unique:true
    }
  },
  nama : {
    type: String
  },
tglahir : {
    type: String
  },
  jeniskelamin:{
    type:String
  },
  alamatskrng : {
    type: String
  },
  nohp :{
    type:String
  },
  email:{
    type:String
  },
  password : {
    type: String
  }
})


module.exports = mongoose.model('cus',customerSchema)
