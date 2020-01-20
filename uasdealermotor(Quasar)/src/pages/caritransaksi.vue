<template>
  <q-page padding>
    <q-card>
      <q-table
        title="Daftar Transaksi"
        :data="data"
        :columns="columns"
        row-key="id"
        :pagination.sync="pagination"
        :loading="loading"
        :filter="filter"
        @request="onRequest"
        binary-state-sort
      >
      <template v-slot:top-right>
      </template>
      <template v-slot:body="props">
          <q-tr :props="props">
            <q-td key="nomorfaktur" :props="props">{{ props.row.nomorfaktur }}</q-td>
            <q-td key="kodemotor" :props="props">{{ props.row.kodemotor }}</q-td>
            <q-td key="username" :props="props">{{ props.row.username }}</q-td>
            <q-td key="tgltransaksi" :props="props">{{ props.row.tgltransaksi }}</q-td>
            <q-td key="jumlahbeli" :props="props">{{ props.row.jumlahbeli }}</q-td>
                   <q-td key="harga" :props="props">{{ props.row.harga }}</q-td>
            <q-td key="bonus" :props="props">{{ props.row.bonus }}</q-td>
              <q-card-actions align="around" class="row q-col-gutter-md no-wrap">
                <div class="col q-gutter-md">
                </div>
              </q-card-actions>
          </q-tr>
        </template>

    </q-table>
    </q-card>
     <q-dialog v-model="OpenEdit" v-if="OpenEdit">
      <q-card style="width: 700px; max-width: 80vw;">
        <q-card-section>
          <div class="text-h6">EDIT</div>
        </q-card-section>

        <q-separator />

        <q-card-section style="max-height: 50vh" class="scroll">
          <q-input label="nomorfaktur" v-model="activedata.nomorfaktur"></q-input>
          <q-input label="kodemotor" v-model="activedata.kodemotor"></q-input>
          <q-input label="username" v-model="activedata.username"></q-input>
          <q-input label="tgltransaksi" v-model="activedata.tgltransaksi"></q-input>
             <q-input label="jumlahbeli" v-model="activedata.jumlahbeli"></q-input>
          <q-input label="harga" v-model="activedata.harga"></q-input>
          <q-input label="bonus" v-model="activedata.bonus"></q-input>
        </q-card-section>
        <q-separator />
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script>
export default {
  name: 'PageIndex',
  data () {
    return {
      filter: '',
      loading: false,
      pagination: {
        sortBy: 'name',
        descending: false,
        page: 1,
        rowsPerPage: 10,
        rowsNumber: 10
      },
      columns: [
        {
          name: 'nomorfaktur',
          required: true,
          label: 'Nomor Faktur',
          align: 'left',
          field: 'nomorfaktur',
          format: val => `${val}`,
          sortable: true
        },
        { name: 'kodemotor', align: 'center', label: 'Kode Motor', field: 'kodemotor', sortable: true },
        { name: 'username', label: 'Username', field: 'username', sortable: true },
        { name: 'tgltransaksi', label: 'Tanggal Transaksi', field: 'tgltransaksi', sortable: true },
        { name: 'jumlahbeli', label: 'Jumlah pembelian', field: 'jumlahbeli', sortable: true },
        { name: 'harga', label: 'Harga', field: 'harga', sortable: true },
        { name: 'bonus', label: 'Bonus', field: 'bonus', sortable: true }
      ],
      data: [],
      original: [],
      OpenEdit: false,
      activedata: null
    }
  },
  created () {
    this.getData()
  },
  mounted () {
    this.onRequest({
      pagination: this.pagination,
      filter: undefined
    })
  },
  methods: {
    getData () {
      this.$axios.get('/transaksi/select')
        .then(res => {
          console.log(res)
          this.original = res.data.data
        })
    },
    onRequest (props) {
      let { page, rowsPerPage, rowsNumber, sortBy, descending } = props.pagination
      let filter = props.filter

      this.loading = true

      // emulate server
      setTimeout(() => {
        // update rowsCount with appropriate value
        this.pagination.rowsNumber = this.getRowsNumberCount(filter)

        // get all rows if "All" (0) is selected
        let fetchCount = rowsPerPage === 0 ? rowsNumber : rowsPerPage

        // calculate starting row of data
        let startRow = (page - 1) * rowsPerPage

        // fetch data from "server"
        let returnedData = this.fetchFromServer(startRow, fetchCount, filter, sortBy, descending)

        // clear out existing data and add new
        this.data.splice(0, this.data.length, ...returnedData)

        // don't forget to update local pagination object
        this.pagination.page = page
        this.pagination.rowsPerPage = rowsPerPage
        this.pagination.sortBy = sortBy
        this.pagination.descending = descending

        // ...and turn of loading indicator
        this.loading = false
      }, 1500)
    },
    fetchFromServer (startRow, count, filter, sortBy, descending) {
      let data = []

      if (!filter) {
        data = this.original.slice(startRow, startRow + count)
      } else {
        let found = 0
        for (let index = startRow, items = 0; index < this.original.length && items < count; ++index) {
          let row = this.original[index]
          // match filter?
          if (!row['name'].includes(filter)) {
            // get a different row, until one is found
            continue
          }
          ++found
          if (found >= startRow) {
            data.push(row)
            ++items
          }
        }
      }

      // handle sortBy
      if (sortBy) {
        data.sort((a, b) => {
          let x = descending ? b : a
          let y = descending ? a : b
          if (sortBy === 'desc') {
            // string sort
            return x[sortBy] > y[sortBy] ? 1 : x[sortBy] < y[sortBy] ? -1 : 0
          } else {
            // numeric sort
            return parseFloat(x[sortBy]) - parseFloat(y[sortBy])
          }
        })
      }

      return data
    },
    getRowsNumberCount (filter) {
      if (!filter) {
        return this.original.length
      }
      let count = 0
      this.original.forEach((treat) => {
        if (treat['name'].includes(filter)) {
          ++count
        }
      })
      return count
    },
    OpenDialog (data) {
      this.OpenEdit = true
      this.activedata = data
    },
    edit () {
      this.$axios.put('motor/update/' + this.activedata._id, this.activedata)
        .then(res => {
          if (res.data.error) {
            this.showNotif(res.data.pesan, 'negative')
          } else {
            this.showNotif(res.data.pesan, 'positive')
            this.OpenEdit = false
            this.getData()
          }
        })
    },
    showNotif (msg, color) {
      this.$q.notify({
        message: msg,
        color: color
      })
    },
    confirm (id) {
      this.$q.dialog({
        title: 'Confirm',
        message: 'Hapus data ?',
        cancel: true,
        persistent: true
      }).onOk(() => {
        this.$axios.delete('/motor/delete/' + id)
          .then(res => {
            if (res.data.error) {
              this.showNotif(res.data.pesan, 'positive')
            } else {
              this.showNotif(res.data.pesan, 'negative')
              this.getData()
            }
          })
      }).onCancel(() => {
        // console.log('>>>> Cancel')
      })
    }
  }
}
</script>
