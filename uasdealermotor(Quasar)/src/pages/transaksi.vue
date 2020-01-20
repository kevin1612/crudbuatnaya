<template>
    <q-page>
       <div class="row justify-center q-pt-md">
         <div class="col-md-6 col-xs-12">
            <q-card flat>
              <q-card-section>
                <div class="text-h5 q-pb-md">BUAT TRANSAKSI</div>
                <q-form
                  @submit="onSubmit"
                  @reset="onReset"
                  class="q-gutter-sm"
                >
                    <q-input
                    filled
                    v-model="nomorfaktur"
                    label="Silahkan isi Nomor Faktur"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Nomor Faktur Tidak Boleh Kosong']"
                  />
                       <q-input
                    filled
                    v-model="kodemotor"
                    label="Silahkan isi Kode Motor"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Kode Motor Tidak Boleh Kosong']"
                  />
                       <q-input
                    filled
                    v-model="username"
                    label="Silahkan isi Username"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Username Tidak Boleh Kosong']"
                  />
                       <q-input
                    filled
                    v-model="tgltransaksi"
                    label="Silahkan isi Tanggal Transaksi hari ini"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Tanggal transaksi Tidak Boleh Kosong']"
                  />
                          <q-select filled v-model="jumlahbeli" :options="listjumlah" label="Jumlah Beli" />
                        <q-input
                    filled
                    v-model="harga"
                    label="Silahkan isi Harga"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Harga Tidak Boleh Kosong']"
                  />
                          <q-input
                    filled
                    v-model="bonus"
                    label="Silahkan isi Bonus yang diberikan"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Bonus Tidak Boleh Kosong']"
                  />

                  <div>
                    <q-btn label="BUAT TRANSAKSI" type="submit" color="primary"/>
                    <q-btn label="RESET" type="reset" color="primary" flat class="q-ml-sm" />
                  </div>
                 </q-form>
              </q-card-section>
            </q-card>
         </div>
       </div>
    </q-page>
</template>

<script>
export default {
  name: 'Guest Layout',
  data () {
    return {
      nomorfaktur: '',
      kodemotor: '',
      username: '',
      tgltransaksi: '',
      jumlahbeli: '',
      harga: '',
      bonus: '',
      listjumlah: [
        '1'
      ]
    }
  },
  methods: {
    onSubmit () {
      try {
        this.$axios.post('transaksi/insert', {
          nomorfaktur: this.nomorfaktur,
          kodemotor: this.kodemotor,
          username: this.username,
          tgltransaksi: this.tgltransaksi,
          jumlahbeli: this.jumlahbeli,
          harga: this.harga,
          bonus: this.bonus
        }).then(res => {
          if (res.data.error) {
            this.showNotif(res.data.pesan, 'negative')
          } else {
            this.showNotif(res.data.pesan, 'positive')
            this.$router.push('/caritransaksi')
          }
        })
      } catch (error) {
        console.log(error)
      }
    },
    onReset () {
      this.nomorfaktur = ''
      this.kodemotor = ''
      this.username = ''
      this.tgltransaksi = ''
      this.jumlahbeli = ''
      this.bonus = ''
    },
    showNotif (msg, color) {
      this.$q.notify({
        message: msg,
        color: 'green'
      })
    }
  }
}
</script>
