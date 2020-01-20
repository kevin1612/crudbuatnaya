const mongoose = require('mongoose')
const Schema = mongoose.Schema

const adminSchema = new Schema({
  kodeadmin : {
    type: String,
    indexes:{
      unique:true
    }
  },
  password : {
    type: String
  },
  nama : {
    type: String
  },
  tglahir : {
    type: String
  },
  jeniskelamin : {
    type: String
  },
  alamatskrng : {
    type: String
  },
  nohp:{
    type:String
  },
  email:{
    type:String
  }
})


module.exports = mongoose.model('adm',adminSchema)
