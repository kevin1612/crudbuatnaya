<template>
    <q-page>
       <div class="row justify-center q-pt-md">
         <div class="col-md-6 col-xs-12">
            <q-card flat>
              <q-card-section>
                <div class="text-h5 q-pb-md">Daftar Semua Motor KONDA</div>
                <q-form
                  @submit="onSubmit"
                  @reset="onReset"
                  class="q-gutter-sm"
                >
                    <q-input
                    filled
                    v-model="kodemotor"
                    label="Silahkan isi Kodemotor"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Kode Motor Tidak Boleh Kosong']"
                  />
                          <q-input
                    filled
                    v-model="namamotor"
                    label="Silahkan isi Nama Motor"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Nama MotorTidak Boleh Kosong']"
                  />

                   <q-select filled v-model="jenismotor" :options="listjenis" label="Pilih jenis pilihan motor" />
                          <q-input
                    filled
                    v-model="warna"
                    label="Silahkan isi Warna Motor"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Warna Tidak Boleh Kosong']"
                  />
                          <q-input
                    filled
                    v-model="stok"
                    label="Silahkan isi Jumlah Unit Motor"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Unit Motor Tidak Boleh Kosong']"
                  />
                              <q-input
                    filled
                    v-model="harga"
                    label="Silahkan isi Harga Motor / unit"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Harga Motor Tidak Boleh Kosong']"
                  />

                  <div>
                    <q-btn label="Daftar Motor" type="submit" color="primary"/>
                    <q-btn label="Reset" type="reset" color="primary" flat class="q-ml-sm" />
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
      kodemotor: '',
      namamotor: '',
      jenismotor: '',
      warna: '',
      stok: '',
      harga: '',
      listjenis: [
        'matic',
        'cub',
        'sport',
        'big bike'
      ]
    }
  },
  methods: {
    onSubmit () {
      try {
        this.$axios.post('motor/insert', {
          kodemotor: this.kodemotor,
          namamotor: this.namamotor,
          jenismotor: this.jenismotor,
          warna: this.warna,
          stok: this.stok,
          harga: this.harga
        }).then(res => {
          if (res.data.error) {
            this.showNotif(res.data.pesan, 'negative')
          } else {
            this.showNotif(res.data.pesan, 'positive')
            this.$router.push('/listmotor')
          }
        })
      } catch (error) {
        console.log(error)
      }
    },
    onReset () {
      this.kodemotor = ''
      this.namamotor = ''
      this.jenismotor = ''
      this.warna = ''
      this.stok = ''
      this.harga = ''
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
