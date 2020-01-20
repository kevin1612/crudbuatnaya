<template>
  <q-page padding>
    <q-card>
      <q-table
        title="Daftar Pemesanan"
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
            <q-td key="kodemotor" :props="props">{{ props.row.kodemotor }}</q-td>
            <q-td key="username" :props="props">{{ props.row.username }}</q-td>
            <q-td key="jumlahbeli" :props="props">{{ props.row.jumlahbeli }}</q-td>
            <q-td key="aksi" :props="props">
              <q-card-actions align="around" class="row q-col-gutter-md no-wrap">
                <div class="col q-gutter-md">
                  <q-btn round color="negative" @click="confirm(props.row._id)" icon="delete"></q-btn>
                </div>
              </q-card-actions>
            </q-td>
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
          <q-input label="Kode motor" v-model="activedata.kodemotor"></q-input>
          <q-input label="Username" v-model="activedata.username"></q-input>
          <q-input label="Jumlah Beli" v-model="activedata.jumlahbeli"></q-input>
        </q-card-section>

        <q-separator />

        <q-card-actions align="right">
          <q-btn flat label="CANCEL" color="primary" v-close-popup />
          <q-btn flat label="EDIT" color="primary" @click="edit()"/>
        </q-card-actions>
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
          name: 'kodemotor',
          required: true,
          label: 'Kode Motor',
          align: 'left',
          field: 'kodemotor',
          format: val => `${val}`,
          sortable: true
        },
        { name: 'username', label: 'Username', field: 'username', sortable: true },
        { name: 'jumlahbeli', label: 'Jumlah Beli', field: 'jumlahbeli', sortable: true },
        { name: 'aksi', label: 'Aksi', field: 'aksi' }
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
      this.$axios.get('/pemesanan/select')
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
      this.$axios.put('pemesanan/update/' + this.activedata._id, this.activedata)
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
        this.$axios.delete('/pemesanan/delete/' + id)
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
