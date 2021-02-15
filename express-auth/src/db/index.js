class Collections {
  constructor() {
    this._arr = [];
  }

  add(item) {
    this._arr = [...this._arr, item];
    return Promise.resolve(this._arr.find(i => i.id === item.id));
  }

  getById(id) {
    return Promise.resolve(this._arr.find(i => i.id === id));
  }

  updateById(id, update) {
    const exists = this._arr.find(item => item.id === id);

    if (!exists) throw new Error(`Item with id ${id} not found`);

    const index = this._arr.indexOf(exists);
    this._arr.splice(index, 1, Object.assign({}, [exists, update]));

    return Promise.resolve(this._arr[index]);
  }

  deleteById(id) {
    const exists = this._arr.find(item => item.id === id);

    if (!exists) throw new Error(`Item with id ${id} not found`);

    const index = this._arr.indexOf(exists);
    this._arr.splice(index, 1);

    return Promise.resolve(exists);
  }
}

class DB {
  constructor() {
    this._collections = new Collections();
  }
}

module.exports = new DB();
