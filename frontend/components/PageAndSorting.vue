<!--Component for display pagination and sorting-->
<!--contains emits for informing parent about state changing-->
<script setup>
const props = defineProps(['totalPages', 'page', 'currentOption'])
const emit = defineEmits(['page-changed', 'sort-changed'])
const handlePageChanges = (value) => {
  emit('page-changed', value)
}

const handleSortChanged = (value) => {
  emit('sort-changed', value)
}

const sortOptions = [
  {
    key: 'NONE',
    displayName: 'Sortuj według'
  },
  {
    key: 'BY_NAME_A_Z',
    displayName: 'Nazwa (A-Z)'
  },
  {
    key: 'BY_NAME_Z_A',
    displayName: 'Nazwa (Z-A)'
  },
  {
    key: 'BY_PRICE_ASC',
    displayName: 'Cena (rosnąco)'
  },
  {
    key: 'BY_PRICE_DESC',
    displayName: 'Cena (malejąco)'
  },
  {
    key: 'DISCOUNT_FIRST',
    displayName: 'Najpierw ze zniżką'
  },
  {
    key: 'DISCOUNT_LAST',
    displayName: 'Najpierw bez zniżki'
  }
]

const selected = ref(sortOptions.filter(option => option.key === props.currentOption)[0].key)

</script>

<template>
  <div class="my-10 flex justify-between">
    <UPagination
        :model-value="page"
        :total="props.totalPages * 10"
        @update:model-value="handlePageChanges"
    />
    <USelectMenu
        id="my-menu"
        :options="sortOptions"
        value-attribute="key"
        option-attribute="displayName"
        v-model="selected"
        @change="handleSortChanged"
    />
  </div>
</template>
