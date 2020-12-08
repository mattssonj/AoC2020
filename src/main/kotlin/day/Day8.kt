package day

class Day8(dirtyInput: List<String>) : Solver<Int> {

    private val instructions = dirtyInput.map { Instruction(it) }

    override fun calculateFirst(): Int {
        val program = Program(instructions)
        program.run()
        return program.accumulatorValue()
    }

    override fun calculateSecond(): Int {
        instructions.forEachIndexed { index, instruction ->
            if (instruction.isSwappable()) {
                val program = swapAndBuildProgram(index)
                program.run()
                if (program.didComplete()) return program.accumulatorValue()
            }
        }
        return 0
    }

    private fun swapAndBuildProgram(indexToBeSwapped: Int): Program {
        val swapped = instructions[indexToBeSwapped].swapInstruction()
        val updatedInstructions = instructions.toMutableList()
        updatedInstructions[indexToBeSwapped] = swapped
        return Program(updatedInstructions)
    }
}

class Program(private val instructions: List<Instruction>) {

    private var memory = SystemMemory()
    private val visitedOperationIndex = mutableSetOf<Int>()
    private var didComplete = false

    fun run() {
        while (!visitedOperationIndex.contains(memory.operation)) {
            visitedOperationIndex.add(memory.operation)
            memory = instructions[memory.operation].execute(memory)
            didComplete = memory.operation >= instructions.size
            if (didComplete) break
        }
    }

    fun accumulatorValue() = memory.accumulator
    fun didComplete() = didComplete

}

data class SystemMemory(val accumulator: Int = 0, val operation: Int = 0)
class Instruction(instruction: String) {

    private val operation: Operation
    private val argument: Int

    init {
        val (op, arg) = instruction.split(" ")
        operation = Operation.valueOf(op)
        argument = arg.toInt()
    }

    fun execute(memory: SystemMemory): SystemMemory {
        return operation.execute(memory, argument)
    }

    fun isSwappable(): Boolean {
        return operation != Operation.acc ||
                (operation == Operation.nop && argument != 0)
    }

    fun swapInstruction(): Instruction {
        val operation = when (operation) {
            Operation.jmp -> Operation.nop
            Operation.nop -> Operation.jmp
            else -> operation
        }
        return Instruction("$operation $argument")
    }
}

enum class Operation {
    nop {
        override fun execute(memory: SystemMemory, opValue: Int): SystemMemory {
            return memory.copy(operation = memory.operation + 1)
        }
    },
    acc {
        override fun execute(memory: SystemMemory, opValue: Int): SystemMemory {
            return memory.copy(accumulator = memory.accumulator + opValue, operation = memory.operation + 1)
        }
    },
    jmp {
        override fun execute(memory: SystemMemory, opValue: Int): SystemMemory {
            return memory.copy(operation = memory.operation + opValue)
        }
    };

    abstract fun execute(memory: SystemMemory, opValue: Int): SystemMemory
}
