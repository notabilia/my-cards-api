package com.notabilia.mycards.models.entities;

import com.notabilia.mycards.models.Model;

interface EntityModel<ID> extends Model {

    ID getId();
}
