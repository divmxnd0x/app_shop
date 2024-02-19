<!--Default layout in application. Should not be used in main page-->
<template>
  <div class="h-screen">
    <ClientOnly>
      <SiteHeader/>
    </ClientOnly>
    <slot class="mb-auto"/>
    <SiteFoot/>
  </div>
</template>